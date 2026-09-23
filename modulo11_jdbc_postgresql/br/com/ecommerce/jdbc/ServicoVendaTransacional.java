package br.com.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Nível 4: Controle de Transação Manual e Integridade ACID Multi-Tabela.
 * 
 * [PROPRIEDADES ACID NO JDBC]:
 * 1. Atomicidade (A): Todas as operações da transação (débito de estoque + inserção do pedido)
 *    são confirmadas juntas no commit(), ou todas são revertidas no rollback() em caso de qualquer falha.
 * 2. Consistência (C): O estoque nunca fica negativo e nenhum pedido é registrado sem a correspondente dedução de estoque.
 * 3. Isolamento (I): Transações concorrentes não enxergam dados parciais não confirmados (Dirty Reads).
 * 4. Durabilidade (D): Uma vez executado o commit(), as alterações são gravadas em disco pelo PostgreSQL.
 */
public class ServicoVendaTransacional {

    /**
     * Executa a operação atômica de checkout de venda:
     * 1. Desativa o autocommit da conexão.
     * 2. Consulta o estoque do produto.
     * 3. Aborta com rollback caso o estoque seja insuficiente.
     * 4. Debita a quantidade vendida na tabela 'produto'.
     * 5. Insere o registro de auditoria na tabela 'pedido'.
     * 6. Efetiva todas as operações com commit().
     * 
     * @param idPedido Identificador único do pedido (ex: "PED-1001").
     * @param codigoProduto Código do produto comprado.
     * @param quantidadeComprada Quantidade a ser debitada.
     * @return true se a transação foi efetivada com sucesso.
     */
    public boolean processarVenda(String idPedido, String codigoProduto, int quantidadeComprada)
            throws ClassNotFoundException, SQLException {

        if (idPedido == null || codigoProduto == null || quantidadeComprada <= 0) {
            System.err.println("[TRANSAÇÃO RECUSADA] Parâmetros de venda inválidos.");
            return false;
        }

        String sqlVerificaEstoque = "SELECT quantidade_estoque, nome, preco FROM produto WHERE codigo = ? FOR UPDATE";
        String sqlDebitaEstoque = "UPDATE produto SET quantidade_estoque = quantidade_estoque - ? WHERE codigo = ?";
        String sqlInserePedido = "INSERT INTO pedido (id_pedido, codigo_produto, quantidade_comprada, data_pedido) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement stmtVerifica = null;
        PreparedStatement stmtDebita = null;
        PreparedStatement stmtInsere = null;
        ResultSet rsEstoque = null;

        try {
            conexao = FabricaConexao.obterConexao();

            // 1. Desativação imediata da efetivação automática de comandos para controle transacional ACID
            conexao.setAutoCommit(false);
            System.out.println("[ACID - START] AutoCommit desativado. Transação manual iniciada para o Pedido: " + idPedido);

            // 2. Consulta de estoque com lock pessimista (FOR UPDATE)
            stmtVerifica = conexao.prepareStatement(sqlVerificaEstoque);
            stmtVerifica.setString(1, codigoProduto);
            rsEstoque = stmtVerifica.executeQuery();

            if (!rsEstoque.next()) {
                throw new IllegalStateException("Produto com código '" + codigoProduto + "' não existe no catálogo.");
            }

            int estoqueAtual = rsEstoque.getInt("quantidade_estoque");
            String nomeProduto = rsEstoque.getString("nome");

            System.out.printf("  [ESTOQUE] Produto: %s | Disponível: %d unid. | Solicitado: %d unid.%n",
                    nomeProduto, estoqueAtual, quantidadeComprada);

            // 3. Validação de Regra de Negócio: Saldo em Estoque
            if (estoqueAtual < quantidadeComprada) {
                throw new IllegalStateException(String.format(
                        "Estoque insuficiente para o produto '%s'! Disponível: %d, Solicitado: %d",
                        nomeProduto, estoqueAtual, quantidadeComprada));
            }

            // 4. Operação 1: Débito de Estoque na tabela 'produto'
            stmtDebita = conexao.prepareStatement(sqlDebitaEstoque);
            stmtDebita.setInt(1, quantidadeComprada);
            stmtDebita.setString(2, codigoProduto);
            int linhasDebito = stmtDebita.executeUpdate();

            if (linhasDebito == 0) {
                throw new SQLException("Falha ao atualizar o estoque do produto.");
            }
            System.out.println("  [OPERAÇÃO 1/2] Estoque debitado com sucesso no buffer da transação.");

            // 5. Operação 2: Inserção do Comprovante na tabela 'pedido'
            stmtInsere = conexao.prepareStatement(sqlInserePedido);
            stmtInsere.setString(1, idPedido);
            stmtInsere.setString(2, codigoProduto);
            stmtInsere.setInt(3, quantidadeComprada);
            stmtInsere.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            int linhasPedido = stmtInsere.executeUpdate();

            if (linhasPedido == 0) {
                throw new SQLException("Falha ao registrar o pedido.");
            }
            System.out.println("  [OPERAÇÃO 2/2] Registro do pedido inserido com sucesso no buffer da transação.");

            // 6. Confirmação Atômica de Todas as Operações (COMMIT)
            conexao.commit();
            System.out.println(">>> [ACID - COMMIT] Transação confirmada com sucesso no PostgreSQL! Venda concluída. <<<");
            return true;

        } catch (Exception e) {
            // 7. Rollback defensivo em caso de qualquer exceção ou falha de regra de negócio
            System.err.println("\n[ACID - FALHA DETECTADA]: " + e.getMessage());
            if (conexao != null) {
                try {
                    System.err.println("[ACID - ROLLBACK] Desfazendo alterações parciais no banco de dados...");
                    conexao.rollback();
                    System.err.println("[ACID - ROLLBACK] Rollback executado com sucesso! Nenhuma alteração foi persistida.");
                } catch (SQLException exRollback) {
                    System.err.println("Erro ao executar rollback: " + exRollback.getMessage());
                }
            }
            return false;

        } finally {
            // 8. Restauração do AutoCommit e encerramento ordenado de recursos
            if (rsEstoque != null) {
                try { rsEstoque.close(); } catch (SQLException ignored) {}
            }
            if (stmtVerifica != null) {
                try { stmtVerifica.close(); } catch (SQLException ignored) {}
            }
            if (stmtDebita != null) {
                try { stmtDebita.close(); } catch (SQLException ignored) {}
            }
            if (stmtInsere != null) {
                try { stmtInsere.close(); } catch (SQLException ignored) {}
            }
            if (conexao != null) {
                try {
                    conexao.setAutoCommit(true); // Restaura estado padrão antes de devolver a conexão
                    conexao.close();
                } catch (SQLException ignored) {}
            }
        }
    }
}
