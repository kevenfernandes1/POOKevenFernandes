package br.com.ecommerce.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class RecursosAvancadosDAO {

    // Nivel 5: Cursor rolavel (TYPE_SCROLL_INSENSITIVE)
    public void demonstrarCursorRolaVel() throws ClassNotFoundException, SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto ORDER BY preco ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = FabricaConexao.obterConexao();
            stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();

            System.out.println("\n--- Teste de Cursor Rolavel ---");

            // Vai para o ultimo registro (mais caro)
            if (rs.last()) {
                System.out.println("Ultimo registro (linha " + rs.getRow() + "): " + rs.getString("nome") + " - R$ " + rs.getBigDecimal("preco"));
            }

            // Volta um registro
            if (rs.previous()) {
                System.out.println("Registro anterior (linha " + rs.getRow() + "): " + rs.getString("nome") + " - R$ " + rs.getBigDecimal("preco"));
            }

            // Vai para o primeiro registro (mais barato)
            if (rs.first()) {
                System.out.println("Primeiro registro (linha " + rs.getRow() + "): " + rs.getString("nome") + " - R$ " + rs.getBigDecimal("preco"));
            }

            // Posiciona na linha 2
            if (rs.absolute(2)) {
                System.out.println("Registro na posicao absoluta 2 (linha " + rs.getRow() + "): " + rs.getString("nome") + " - R$ " + rs.getBigDecimal("preco"));
            }

        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Nivel 5: Chamar Stored Procedure com CallableStatement
    public void executarProcedureSaldo(String codigoProduto) throws ClassNotFoundException, SQLException {
        String sql = "{call sp_calcular_saldo_estoque(?, ?, ?)}";
        Connection conn = null;
        CallableStatement callStmt = null;

        try {
            conn = FabricaConexao.obterConexao();
            callStmt = conn.prepareCall(sql);

            // Parametro IN
            callStmt.setString(1, codigoProduto);

            // Parametros OUT
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.registerOutParameter(3, Types.NUMERIC);

            callStmt.execute();

            int quantidade = callStmt.getInt(2);
            BigDecimal totalReais = callStmt.getBigDecimal(3);

            System.out.println("\n--- Resultado da Stored Procedure ---");
            System.out.println("Codigo: " + codigoProduto);
            System.out.println("Saldo em estoque: " + quantidade + " unidades");
            System.out.println("Total em reais: R$ " + totalReais);

        } finally {
            if (callStmt != null) callStmt.close();
            if (conn != null) conn.close();
        }
    }
}
