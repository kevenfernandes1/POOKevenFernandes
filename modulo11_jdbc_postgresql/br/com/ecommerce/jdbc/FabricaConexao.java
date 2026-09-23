package br.com.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica centralizada de conexões JDBC com o banco de dados PostgreSQL.
 * 
 * [CONCEITOS ARQUITETURAIS JDBC]:
 * 1. O pacote java.sql define um conjunto de interfaces polimórficas (Connection, Statement, ResultSet).
 * 2. O driver do PostgreSQL (org.postgresql.Driver) fornece a implementação concreta dessas interfaces.
 * 3. O método Class.forName("org.postgresql.Driver") carrega dinamicamente a classe do driver na JVM,
 *    registrando-o junto ao DriverManager.
 */
public class FabricaConexao {

    // Configurações padrão de conexão com o PostgreSQL
    private static final String DRIVER_CLASS = "org.postgresql.Driver";
    private static final String URL_PADRAO = "jdbc:postgresql://localhost:5432/bdecommerce";
    private static final String USUARIO_PADRAO = "postgres";
    private static final String SENHA_PADRAO = "postgres";

    /**
     * Obtém uma conexão ativa com as credenciais padrão.
     * @return Conexão aberta com o PostgreSQL (instância concreta que realiza java.sql.Connection).
     * @throws ClassNotFoundException Caso o driver postgresql.jar não esteja no Classpath.
     * @throws SQLException Caso ocorra erro de autenticação, porta inacessível ou banco inexistente.
     */
    public static Connection obterConexao() throws ClassNotFoundException, SQLException {
        return obterConexao(URL_PADRAO, USUARIO_PADRAO, SENHA_PADRAO);
    }

    /**
     * Obtém uma conexão ativa permitindo parametrização customizada de URL e credenciais.
     */
    public static Connection obterConexao(String url, String usuario, String senha) throws ClassNotFoundException, SQLException {
        // 1. Carga dinâmica do bytecode do driver no ClassLoader da JVM
        Class.forName(DRIVER_CLASS);

        // 2. Estabelecimento do canal de comunicação via DriverManager
        return DriverManager.getConnection(url, usuario, senha);
    }

    public static String getUrlPadrao() {
        return URL_PADRAO;
    }

    public static String getDriverClass() {
        return DRIVER_CLASS;
    }
}
