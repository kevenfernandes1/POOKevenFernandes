package br.com.ecommerce.jdbc;

import br.com.ecommerce.model.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para gerenciamento da persistência da entidade Produto.
 * Cobre os requisitos do NÍVEL 2 (Operações DML Seguras) e NÍVEL 3 (Consultas e Cursores).
 */
public class ProdutoDAO {

    // =========================================================================
    // NÍVEL 2: OPERAÇÕES DML SEGURAS E PREVENÇÃO A SQL INJECTION
    // =========================================================================

    /**
     * Insere um novo produto no banco de dados utilizando PreparedStatement parametrizado.
     * Previne ataques de SQL Injection e garante precisão monetária com BigDecimal.
     * 
     * @param produto Objeto produto a ser persistido.
     * @return true se o registro foi inserido com sucesso (linhas afetadas > 0).
     */
    public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException {
        if (produto == null) return false;

        String sql = "INSERT INTO produto (codigo, nome, preco, quantidade_estoque) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement(sql);

            // Mapeamento posicional seguro dos parâmetros
            comando.setString(1, produto.getCodigo());
            comando.setString(2, produto.getNome());
            comando.setBigDecimal(3, produto.getPreco()); // Mapeamento exato de NUMERIC(10,2)
            comando.setInt(4, produto.getQuantidadeEstoque());

            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas > 0;

        } finally {
            // Fechamento defensivo e explícito em bloco de finalização
            if (comando != null) {
                try { comando.close(); } catch (SQLException ignored) {}
            }
            if (conexao != null) {
                try { conexao.close(); } catch (SQLException ignored) {}
            }
        }
    }

    /**
     * Atualiza o preço unitário de um produto existente.
     */
    public boolean atualizarPreco(String codigo, BigDecimal novoPreco) throws ClassNotFoundException, SQLException {
        if (codigo == null || novoPreco == null) return false;

        String sql = "UPDATE produto SET preco = ? WHERE codigo = ?";
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement(sql);

            comando.setBigDecimal(1, novoPreco);
            comando.setString(2, codigo);

            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas > 0;

        } finally {
            if (comando != null) {
                try { comando.close(); } catch (SQLException ignored) {}
            }
            if (conexao != null) {
                try { conexao.close(); } catch (SQLException ignored) {}
            }
        }
    }

    /**
     * Exclui um produto do catálogo pelo seu código identificador.
     */
    public boolean excluir(String codigo) throws ClassNotFoundException, SQLException {
        if (codigo == null) return false;

        String sql = "DELETE FROM produto WHERE codigo = ?";
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement(sql);

            comando.setString(1, codigo);

            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas > 0;

        } finally {
            if (comando != null) {
                try { comando.close(); } catch (SQLException ignored) {}
            }
            if (conexao != null) {
                try { conexao.close(); } catch (SQLException ignored) {}
            }
        }
    }

    // =========================================================================
    // NÍVEL 3: CONSULTAS, NAVEGAÇÃO POR CURSOR E MAPEAMENTO OBJETO-RELACIONAL
    // =========================================================================

    /**
     * Recupera todos os produtos cadastrados ordenados por nome em ordem alfabética.
     * Demonstra extração alternada por índice posicional e por nome de coluna.
     * Assegura o fechamento em cascata dos 3 recursos: ResultSet -> Statement -> Connection.
     */
    public List<Produto> listarTodos() throws ClassNotFoundException, SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto ORDER BY nome ASC";
        List<Produto> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet rs = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement(sql);
            rs = comando.executeQuery();

            // Percorrendo as tuplas do cursor sequencial
            while (rs.next()) {
                // 1. Extração por índice posicional (1-based no JDBC)
                String codigo = rs.getString(1);

                // 2. Extração por identificador nomeado da coluna
                String nome = rs.getString("nome");
                BigDecimal preco = rs.getBigDecimal("preco");
                int estoque = rs.getInt("quantidade_estoque");

                Produto p = new Produto(codigo, nome, preco, estoque);
                lista.add(p);
            }

            return lista;

        } finally {
            // Encerramento ordenado em cascata
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
     * Realiza a busca de um produto específico pelo código identificador.
     */
    public Produto buscarPorCodigo(String codigo) throws ClassNotFoundException, SQLException {
        if (codigo == null) return null;

        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto WHERE codigo = ?";
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet rs = null;

        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement(sql);
            comando.setString(1, codigo);

            rs = comando.executeQuery();

            // Se o cursor encontrar registro, popula e retorna o objeto
            if (rs.next()) {
                String cod = rs.getString("codigo");
                String nome = rs.getString("nome");
                BigDecimal preco = rs.getBigDecimal("preco");
                int estoque = rs.getInt("quantidade_estoque");

                return new Produto(cod, nome, preco, estoque);
            }

            return null; // Registro inexistente

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
}
