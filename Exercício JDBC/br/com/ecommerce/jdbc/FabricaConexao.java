package br.com.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/bdecommerce";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection obterConexao() throws ClassNotFoundException, SQLException {
        // Carrega o driver do PostgreSQL
        Class.forName("org.postgresql.Driver");
        // Retorna a conexao com o banco
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
