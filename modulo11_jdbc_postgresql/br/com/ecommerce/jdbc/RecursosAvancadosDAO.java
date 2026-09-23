package br.com.ecommerce.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Nível 5: Recursos Avançados do JDBC (Cursores Roláveis e Stored Procedures).
 * 
 * [CARACTERÍSTICAS TÉCNICAS]:
 * 1. TYPE_SCROLL_INSENSITIVE: Permite a movimentação bidirecional e posicional absoluta do cursor
 *    sem que ele seja sensível a alterações externas ocorridas durante a consulta.
 * 2. CallableStatement: Interface especializada para invocação de rotinas compiladas no servidor (Stored Procedures),
 *    permitindo o tráfego de parâmetros de entrada (IN) e de saída (OUT).
 */
public class RecursosAvancadosDAO {

    /**
     * Demonstra a navegação não-linear e bidirecional de um cursor rolável (Scrollable ResultSet).
     */
    public void demonstrarCursorRolaVel() throws ClassNotFoundException, SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto ORDER BY preco ASC";

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet rs = null;

        try {
            conexao = FabricaConexao.obterConexao();

            // Configuração do comando com tipo de cursor rolável e concorrência somente-leitura
            comando = conexao.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            rs = comando.executeQuery();

            System.out.println("\n--- [DEMONSTRAÇÃO DE CURSOR ROLÁVEL - TYPE_SCROLL_INSENSITIVE] ---");

            // 1. Navegação para a última linha (registro mais caro)
            if (rs.last()) {
                int totalLinhas = rs.getRow();
                System.out.printf("  -> rs.last(): Posicionado no ÚLTIMO registro (Linha %d):%n", totalLinhas);
                System.out.printf("     [%s] %s - R$ %.2f (Estoque: %d)%n",
                        rs.getString("codigo"), rs.getString("nome"), rs.getBigDecimal("preco"), rs.getInt("quantidade_estoque"));
            }

            // 2. Navegação retrocedendo uma linha (penúltimo registro)
            if (rs.previous()) {
                System.out.printf("  -> rs.previous(): Retrocedeu para a Linha %d:%n", rs.getRow());
                System.out.printf("     [%s] %s - R$ %.2f (Estoque: %d)%n",
                        rs.getString("codigo"), rs.getString("nome"), rs.getBigDecimal("preco"), rs.getInt("quantidade_estoque"));
            }

            // 3. Salto direto para a primeira linha (registro mais barato)
            if (rs.first()) {
                System.out.printf("  -> rs.first(): Saltou para o PRIMEIRO registro (Linha %d):%n", rs.getRow());
                System.out.printf("     [%s] %s - R$ %.2f (Estoque: %d)%n",
                        rs.getString("codigo"), rs.getString("nome"), rs.getBigDecimal("preco"), rs.getInt("quantidade_estoque"));
            }

            // 4. Posicionamento absoluto na segunda linha retornada
            if (rs.absolute(2)) {
                System.out.printf("  -> rs.absolute(2): Posicionado exatamente na Linha 2:%n", rs.getRow());
                System.out.printf("     [%s] %s - R$ %.2f (Estoque: %d)%n",
                        rs.getString("codigo"), rs.getString("nome"), rs.getBigDecimal("preco"), rs.getInt("quantidade_estoque"));
            }

        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException ignored) {}
            }
            if (comando != null) {
                try { comando.close(); } catch (SQLException ignored) {}
            }
            if (conexao != null) {
                try { conexao.close(); } catch (SQLException ignored) {}
            }
        }
    }

    /**
     * Executa a Stored Procedure corporativa 'sp_calcular_saldo_estoque' utilizando CallableStatement.
     * 
     * @param codigoProduto Código identificador do produto para cálculo patrimonial.
     */
    public void executarProcedureSaldo(String codigoProduto) throws ClassNotFoundException, SQLException {
        if (codigoProduto == null) return;

        // Chamada padronizada JDBC para stored procedures
        String sqlProcedure = "{call sp_calcular_saldo_estoque(?, ?, ?)}";

        Connection conexao = null;
        CallableStatement callStmt = null;

        try {
            conexao = FabricaConexao.obterConexao();
            callStmt = conexao.prepareCall(sqlProcedure);

            // 1. Mapeamento do parâmetro de entrada (IN)
            callStmt.setString(1, codigoProduto);

            // 2. Registro dos tipos SQL para os parâmetros de saída (OUT)
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.registerOutParameter(3, Types.NUMERIC);

            System.out.println("\n--- [EXECUÇÃO DE STORED PROCEDURE NO POSTGRESQL] ---");
            System.out.println("  Invocando: sp_calcular_saldo_estoque('" + codigoProduto + "')...");

            // 3. Execução da rotina compilada no banco de dados
            callStmt.execute();

            // 4. Captura dos valores retornados pelos parâmetros OUT
            int quantidadeEstoque = callStmt.getInt(2);
            BigDecimal valorTotalPatrimonial = callStmt.getBigDecimal(3);

            System.out.println("  >>> RESULTADOS RETORNADOS PELA PROCEDURE <<<");
            System.out.println("  Código do Produto Analisado : " + codigoProduto);
            System.out.println("  Saldo Físico em Estoque     : " + quantidadeEstoque + " unidades");
            System.out.printf( "  Valor Patrimonial Total     : R$ %s%n",
                    (valorTotalPatrimonial != null ? valorTotalPatrimonial.toString() : "0.00"));

        } finally {
            if (callStmt != null) {
                try { callStmt.close(); } catch (SQLException ignored) {}
            }
            if (conexao != null) {
                try { conexao.close(); } catch (SQLException ignored) {}
            }
        }
    }
}
