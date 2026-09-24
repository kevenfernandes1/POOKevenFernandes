package br.com.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexaoPostgreSQL {

    public static void main(String[] args) {
        Connection conexao = null;

        try {
            System.out.println("Tentando conectar ao PostgreSQL...");
            conexao = FabricaConexao.obterConexao();

            System.out.println("Conexao realizada com sucesso!");
            System.out.println("Classe concreta do driver: " + conexao.getClass().getName());

        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver do PostgreSQL nao encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                    System.out.println("Conexao fechada.");
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexao: " + e.getMessage());
                }
            }
        }
    }
}
