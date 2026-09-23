package br.com.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Nível 1: Validação de Conectividade e Demonstração do Polimorfismo da Interface Connection.
 */
public class TesteConexaoPostgreSQL {

    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println(" NÍVEL 1: FUNDAMENTOS JDBC, CARGA DE DRIVER E POLIMORFISMO DE CONEXÃO");
        System.out.println("======================================================================");

        Connection conexao = null;

        try {
            System.out.println("\n[1] Tentando carregar o driver (" + FabricaConexao.getDriverClass() + ")...");
            System.out.println("[2] Conectando ao PostgreSQL em: " + FabricaConexao.getUrlPadrao());

            conexao = FabricaConexao.obterConexao();

            System.out.println("\n>>> CONEXÃO ESTABELECIDA COM SUCESSO! <<<");

            // Demonstração do Polimorfismo: A interface java.sql.Connection é realizada por uma classe concreta do driver
            String nomeClasseConcreta = conexao.getClass().getName();
            System.out.println("\n[DEMONSTRAÇÃO DE POLIMORFISMO]");
            System.out.println("  Interface declarada em Java: java.sql.Connection");
            System.out.println("  Classe concreta em execução: " + nomeClasseConcreta);
            System.out.println("  AutoCommit padrão da conexão: " + conexao.getAutoCommit());
            System.out.println("  Catálogo / Banco atual: " + conexao.getCatalog());

        } catch (ClassNotFoundException e) {
            System.err.println("\n[ERRO DE DRIVER - ClassNotFoundException]:");
            System.err.println("  O driver JDBC do PostgreSQL não foi localizado no Classpath da aplicação.");
            System.err.println("  Certifique-se de incluir o arquivo 'lib/postgresql-42.7.4.jar' ao executar.");
            System.err.println("  Detalhe: " + e.getMessage());

        } catch (SQLException e) {
            System.err.println("\n[ERRO DE BANCO DE DADOS - SQLException]:");
            System.err.println("  Falha ao comunicar com o servidor PostgreSQL.");
            System.err.println("  Verifique se o serviço do PostgreSQL está rodando na porta 5432, se o banco 'bdecommerce'");
            System.err.println("  foi criado e se o usuário/senha informados estão corretos.");
            System.err.println("  SQLState: " + e.getSQLState() + " | Código de Erro: " + e.getErrorCode());
            System.err.println("  Mensagem: " + e.getMessage());

        } finally {
            // Fechamento explícito no bloco finally garantindo a devolução de recursos para o SGBD
            if (conexao != null) {
                try {
                    conexao.close();
                    System.out.println("\n[FECHAMENTO] Conexão encerrada com sucesso via conexao.close().");
                } catch (SQLException e) {
                    System.err.println("Erro ao tentar fechar a conexão: " + e.getMessage());
                }
            }
        }

        System.out.println("======================================================================");
    }
}
