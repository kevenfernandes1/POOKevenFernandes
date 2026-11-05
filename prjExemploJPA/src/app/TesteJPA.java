package app;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Empregado;

public class TesteJPA {

    public static void main(String[] args) {
        // Inicializa a fabrica com a persistence-unit do persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PuMvcJpa");
        EntityManager em = emf.createEntityManager();

        try {
            // Operacao 1: Insercao (Create)
            em.getTransaction().begin();

            Empregado novoFuncionario = new Empregado("Carlos Eduardo", "Analista de Sistemas", 6500.00);
            em.persist(novoFuncionario);

            em.getTransaction().commit();
            System.out.println("Registro persistido com sucesso! ID gerado: " + novoFuncionario.getId());

            // Operacao 2: Consulta por Chave Primaria (Read)
            Long idPesquisa = novoFuncionario.getId();
            Empregado registroEncontrado = em.find(Empregado.class, idPesquisa);
            System.out.println("Consulta por ID: " + registroEncontrado);

            // Operacao 3: Consulta customizada com JPQL
            String jpql = "SELECT e FROM Empregado e WHERE e.salario >= :minSalario";
            TypedQuery<Empregado> query = em.createQuery(jpql, Empregado.class);
            query.setParameter("minSalario", 5000.00);

            List<Empregado> listaFiltrada = query.getResultList();
            System.out.println("--- Funcionarios com Salario >= 5000 ---");
            for (Empregado emp : listaFiltrada) {
                System.out.println(emp.getNome() + " | Cargo: " + emp.getCargo() + " | R$ " + emp.getSalario());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
