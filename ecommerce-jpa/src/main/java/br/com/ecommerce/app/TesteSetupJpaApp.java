package br.com.ecommerce.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteSetupJpaApp {

    public static void main(String[] args) {
        System.out.println("Inicializando EntityManagerFactory para PuEcommerceJpa...");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("PuEcommerceJpa");
            em = emf.createEntityManager();

            System.out.println("Hibernate inicializado com sucesso!");
            System.out.println("EntityManager ativo: " + em.isOpen());
        } catch (Exception e) {
            System.err.println("Falha ao inicializar o JPA/Hibernate:");
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
            System.out.println("Recursos encerrados.");
        }
    }
}
