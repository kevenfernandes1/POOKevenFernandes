package br.com.ecommerce.jdbc;

import br.com.ecommerce.model.Produto;
import java.math.BigDecimal;
import java.util.List;

public class MainJDBCIntegrador {

    public static void main(String[] args) {
        System.out.println("Iniciando testes do modulo 11 (JDBC e PostgreSQL)...");

        try {
            // Nivel 1: Teste de conexao
            System.out.println("\n--- Nivel 1: Conexao ---");
            TesteConexaoPostgreSQL.main(args);

            ProdutoDAO produtoDAO = new ProdutoDAO();

            // Nivel 2: Inserir e atualizar
            System.out.println("\n--- Nivel 2: DML com PreparedStatement ---");
            Produto p = new Produto("P99", "Teclado Mecanico", new BigDecimal("250.00"), 10);
            produtoDAO.excluir("P99"); // limpa se ja existir
            produtoDAO.inserir(p);
            System.out.println("Produto inserido com sucesso!");

            produtoDAO.atualizarPreco("P99", new BigDecimal("230.00"));
            System.out.println("Preco atualizado!");

            // Nivel 3: Consultas
            System.out.println("\n--- Nivel 3: Listagem de Produtos ---");
            List<Produto> lista = produtoDAO.listarTodos();
            for (Produto prod : lista) {
                System.out.println(prod);
            }

            Produto buscado = produtoDAO.buscarPorCodigo("P99");
            System.out.println("Busca por codigo (P99): " + buscado);

            // Nivel 4: Transacao ACID
            System.out.println("\n--- Nivel 4: Transacao ---");
            ServicoVendaTransacional venda = new ServicoVendaTransacional();
            venda.processarVenda("PED01", "P99", 2);  // venda valida
            venda.processarVenda("PED02", "P99", 50); // deve falhar por falta de estoque

            // Nivel 5: Cursor e Procedure
            System.out.println("\n--- Nivel 5: Recursos Avancados ---");
            RecursosAvancadosDAO avancado = new RecursosAvancadosDAO();
            avancado.demonstrarCursorRolaVel();
            avancado.executarProcedureSaldo("P99");

        } catch (Exception e) {
            System.out.println("Erro na execucao: " + e.getMessage());
        }
    }
}
