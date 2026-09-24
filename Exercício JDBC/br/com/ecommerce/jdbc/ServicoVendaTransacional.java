package br.com.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ServicoVendaTransacional {

    // Nivel 4: Transacao manual com setAutoCommit(false), commit e rollback
    public boolean processarVenda(String idPedido, String codigoProduto, int quantidadeComprada) throws ClassNotFoundException, SQLException {
        String sqlSelect = "SELECT quantidade_estoque FROM produto WHERE codigo = ?";
        String sqlUpdate = "UPDATE produto SET quantidade_estoque = quantidade_estoque - ? WHERE codigo = ?";
        String sqlInsert = "INSERT INTO pedido (id_pedido, codigo_produto, quantidade_comprada, data_pedido) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmtSelect = null;
        PreparedStatement stmtUpdate = null;
        PreparedStatement stmtInsert = null;
        ResultSet rs = null;

        try {
            conn = FabricaConexao.obterConexao();
            
            // Desativa o autocommit para controlar a transacao manualmente
            conn.setAutoCommit(false);

            // 1. Verifica se tem estoque
            stmtSelect = conn.prepareStatement(sqlSelect);
            stmtSelect.setString(1, codigoProduto);
            rs = stmtSelect.executeQuery();

            if (!rs.next()) {
                throw new Exception("Produto nao encontrado no catalogo.");
            }

            int estoqueAtual = rs.getInt("quantidade_estoque");
            if (estoqueAtual < quantidadeComprada) {
                throw new Exception("Estoque insuficiente. Saldo atual: " + estoqueAtual + ", solicitado: " + quantidadeComprada);
            }

            // 2. Debita do estoque
            stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setInt(1, quantidadeComprada);
            stmtUpdate.setString(2, codigoProduto);
            stmtUpdate.executeUpdate();

            // 3. Insere o pedido
            stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setString(1, idPedido);
            stmtInsert.setString(2, codigoProduto);
            stmtInsert.setInt(3, quantidadeComprada);
            stmtInsert.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmtInsert.executeUpdate();

            // Se deu tudo certo, confirma a transacao
            conn.commit();
            System.out.println("Venda realizada com sucesso! Pedido: " + idPedido);
            return true;

        } catch (Exception e) {
            System.out.println("Erro durante a venda: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rollback executado com sucesso.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (rs != null) rs.close();
            if (stmtSelect != null) stmtSelect.close();
            if (stmtUpdate != null) stmtUpdate.close();
            if (stmtInsert != null) stmtInsert.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
