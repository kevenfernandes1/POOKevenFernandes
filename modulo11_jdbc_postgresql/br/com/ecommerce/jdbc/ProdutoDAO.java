package br.com.ecommerce.jdbc;

import br.com.ecommerce.model.Produto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Nivel 2: Inserir produto com PreparedStatement
    public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO produto (codigo, nome, preco, quantidade_estoque) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = FabricaConexao.obterConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidadeEstoque());

            int linhas = stmt.executeUpdate();
            return linhas > 0;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Nivel 2: Atualizar preco
    public boolean atualizarPreco(String codigo, BigDecimal novoPreco) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE produto SET preco = ? WHERE codigo = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = FabricaConexao.obterConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setBigDecimal(1, novoPreco);
            stmt.setString(2, codigo);

            int linhas = stmt.executeUpdate();
            return linhas > 0;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Nivel 2: Excluir produto
    public boolean excluir(String codigo) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM produto WHERE codigo = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = FabricaConexao.obterConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, codigo);

            int linhas = stmt.executeUpdate();
            return linhas > 0;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Nivel 3: Listar todos os produtos
    public List<Produto> listarTodos() throws ClassNotFoundException, SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto ORDER BY nome ASC";
        List<Produto> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = FabricaConexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Pegando por indice e por nome da coluna
                String codigo = rs.getString(1);
                String nome = rs.getString("nome");
                BigDecimal preco = rs.getBigDecimal("preco");
                int estoque = rs.getInt("quantidade_estoque");

                Produto p = new Produto(codigo, nome, preco, estoque);
                lista.add(p);
            }
            return lista;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Nivel 3: Buscar por codigo
    public Produto buscarPorCodigo(String codigo) throws ClassNotFoundException, SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto WHERE codigo = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = FabricaConexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String cod = rs.getString("codigo");
                String nome = rs.getString("nome");
                BigDecimal preco = rs.getBigDecimal("preco");
                int estoque = rs.getInt("quantidade_estoque");

                return new Produto(cod, nome, preco, estoque);
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
