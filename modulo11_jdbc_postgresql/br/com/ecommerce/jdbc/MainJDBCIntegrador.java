package br.com.ecommerce.jdbc;

import br.com.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * Aplicação Executável Integradora do Módulo 11 (JDBC & PostgreSQL).
 * Demonstra em sequência todos os 5 níveis solicitados no roteiro prático.
 */
public class MainJDBCIntegrador {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println(" PROJETO INTEGRADOR: PERSISTÊNCIA RELACIONAL EM JAVA COM JDBC E POSTGRESQL     ");
        System.out.println(" Disciplina: Programação Orientada a Objetos | Módulo 11: Java DataBase Connect ");
        System.out.println("================================================================================");

        try {
            // =========================================================================
            // NÍVEL 1: FUNDAMENTOS DE ARQUITETURA, CARGA DE DRIVER E POLIMORFISMO
            // =========================================================================
            System.out.println("\n################################################################################");
            System.out.println(">>> NÍVEL 1: CARGA DE DRIVER E POLIMORFISMO DE CONEXÃO <<<");
            System.out.println("################################################################################");

            try (Connection conexao = FabricaConexao.obterConexao()) {
                System.out.println("-> Conexão com PostgreSQL bem-sucedida!");
                System.out.println("-> Interface: java.sql.Connection");
                System.out.println("-> Classe Concreta (Driver): " + conexao.getClass().getName());
                System.out.println("-> Catálogo Atual: " + conexao.getCatalog());
            }

            ProdutoDAO produtoDAO = new ProdutoDAO();

            // =========================================================================
            // NÍVEL 2: OPERAÇÕES DML SEGURAS E PREVENÇÃO A SQL INJECTION
            // =========================================================================
            System.out.println("\n################################################################################");
            System.out.println(">>> NÍVEL 2: OPERAÇÕES DML SEGURAS COM PREPAREDSTATEMENT <<<");
            System.out.println("################################################################################");

            Produto novoProduto = new Produto("PRD999", "Headset Bluetooth Pro Noise Cancelling", new BigDecimal("750.00"), 25);

            System.out.println("\n1. Inserindo novo produto com PreparedStatement...");
            // Remove antes se existir para teste idempotente
            produtoDAO.excluir("PRD999");
            boolean inserido = produtoDAO.inserir(novoProduto);
            System.out.println("  Resultado da inserção: " + (inserido ? "SUCESSO" : "FALHA"));

            System.out.println("\n2. Atualizando preço com precisão monetária (BigDecimal)...");
            boolean atualizado = produtoDAO.atualizarPreco("PRD999", new BigDecimal("699.90"));
            System.out.println("  Resultado da atualização de preço: " + (atualizado ? "SUCESSO" : "FALHA"));

            // =========================================================================
            // NÍVEL 3: CONSULTAS, NAVEGAÇÃO POR CURSOR E MAPEAMENTO ORM
            // =========================================================================
            System.out.println("\n################################################################################");
            System.out.println(">>> NÍVEL 3: CONSULTAS, RESULTSET E MAPEAMENTO OBJETO-RELACIONAL <<<");
            System.out.println("################################################################################");

            System.out.println("\n1. Listagem completa de produtos do catálogo:");
            List<Produto> catalogo = produtoDAO.listarTodos();
            for (Produto p : catalogo) {
                System.out.println("  " + p);
            }

            System.out.println("\n2. Busca por código específico ('PRD999'):");
            Produto buscado = produtoDAO.buscarPorCodigo("PRD999");
            System.out.println("  Produto recuperado: " + buscado);

            // =========================================================================
            // NÍVEL 4: CONTROLE TRANSAÇÃO MANUAL E INTEGRIDADE ACID
            // =========================================================================
            System.out.println("\n################################################################################");
            System.out.println(">>> NÍVEL 4: TRANSAÇÕES ACID MULTI-TABELA (COMMIT E ROLLBACK) <<<");
            System.out.println("################################################################################");

            ServicoVendaTransacional servicoVenda = new ServicoVendaTransacional();

            System.out.println("\n--- Cenário 1: Venda Válida com Saldo em Estoque (Dedução + Inserção Pedido) ---");
            boolean venda1 = servicoVenda.processarVenda("PED-8001", "PRD999", 5);
            System.out.println("Status final do Cenário 1: " + (venda1 ? "EFETIVADO (COMMIT)" : "FALHOU"));

            System.out.println("\n--- Cenário 2: Venda com Saldo Insuficiente (Disparo de Exceção + ROLLBACK) ---");
            boolean venda2 = servicoVenda.processarVenda("PED-8002", "PRD999", 1000); // Quantidade superior ao estoque
            System.out.println("Status final do Cenário 2: " + (venda2 ? "EFETIVADO" : "CANCELADO COM SUCESSO (ROLLBACK)"));

            // =========================================================================
            // NÍVEL 5: RECURSOS AVANÇADOS (CURSORES ROLÁVEIS E STORED PROCEDURES)
            // =========================================================================
            System.out.println("\n################################################################################");
            System.out.println(">>> NÍVEL 5: CURSORES ROLÁVEIS E STORED PROCEDURES <<<");
            System.out.println("################################################################################");

            RecursosAvancadosDAO recursosAvancados = new RecursosAvancadosDAO();

            System.out.println("\n1. Demonstração de navegação não-linear (first, last, previous, absolute):");
            recursosAvancados.demonstrarCursorRolaVel();

            System.out.println("\n2. Invocação de Stored Procedure compilada no PostgreSQL (CallableStatement):");
            recursosAvancados.executarProcedureSaldo("PRD999");

            System.out.println("\n================================================================================");
            System.out.println("               TODOS OS NÍVEIS FORAM EXECUTADOS COM SUCESSO!                   ");
            System.out.println("================================================================================");

        } catch (ClassNotFoundException e) {
            System.err.println("\n[ERRO DE CONFIGURAÇÃO]: Driver PostgreSQL não encontrado no Classpath.");
            System.err.println("Inclua a flag '-cp \"lib/postgresql-42.7.4.jar;.\"' na compilação/execução.");

        } catch (Exception e) {
            System.out.println("\n[OBSERVAÇÃO AMBIENTAL]:");
            System.out.println("Para executar contra um banco PostgreSQL ativo:");
            System.out.println("1. Inicie o PostgreSQL na porta 5432.");
            System.out.println("2. Execute o script 'modulo11_jdbc_postgresql/sql/schema_ecommerce.sql'.");
            System.out.println("3. Execute: java -cp \"lib/postgresql-42.7.4.jar;bin\" br.com.ecommerce.jdbc.MainJDBCIntegrador");
            System.out.println("\nDetalhe técnico da exceção capturada: " + e.getMessage());
        }
    }
}
