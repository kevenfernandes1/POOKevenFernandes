package br.com.ecommerce.app;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.ecommerce.dao.ProdutoConsultasDAO;
import br.com.ecommerce.modelo.Categoria;
import br.com.ecommerce.modelo.ProdutoJpa;
import br.com.ecommerce.util.JPAUtil;

public class AppConsultasJpql {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoConsultasDAO dao = new ProdutoConsultasDAO(em);

        try {
            // 1. Populando dados para os testes de JPQL
            em.getTransaction().begin();

            Categoria hardware = new Categoria("Hardware", "HDW");
            Categoria games = new Categoria("Games", "GMS");

            ProdutoJpa p1 = new ProdutoJpa("HDW-01", "Processador Octa Core", new BigDecimal("1200.00"), 10, hardware);
            ProdutoJpa p2 = new ProdutoJpa("HDW-02", "Memória RAM 16GB DDR5", new BigDecimal("380.00"), 25, hardware);
            ProdutoJpa p3 = new ProdutoJpa("HDW-03", "SSD NVMe 1TB Gen4", new BigDecimal("490.00"), 18, hardware);
            ProdutoJpa p4 = new ProdutoJpa("GMS-01", "Controle Sem Fio Wireless", new BigDecimal("320.00"), 30, games);
            ProdutoJpa p5 = new ProdutoJpa("GMS-02", "Console Portátil 512GB", new BigDecimal("2800.00"), 8, games);

            em.persist(hardware);
            em.persist(games);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);

            em.getTransaction().commit();
            System.out.println("Catálogo de testes cadastrado com sucesso.");

            // 2. Teste: JPQL dinamica com parametro nomeado :teto
            System.out.println("\n--- 1. JPQL Dinâmica: Produtos com preço até R$ 500,00 ---");
            List<ProdutoJpa> produtosBaratos = dao.listarPorPrecoMaximo(new BigDecimal("500.00"));
            for (ProdutoJpa p : produtosBaratos) {
                System.out.println("  -> " + p.getNome() + " | R$ " + p.getPreco());
            }

            // 3. Teste: Navegacao por relacionamento (p.categoria.nome)
            System.out.println("\n--- 2. Consulta por Navegação: Categoria contendo 'Hardware' ---");
            List<ProdutoJpa> produtosHardware = dao.buscarPorNomeCategoria("hardware");
            for (ProdutoJpa p : produtosHardware) {
                System.out.println("  -> " + p.getNome() + " | Cat: " + p.getCategoria().getNome() + " | R$ " + p.getPreco());
            }

            // 4. Teste: @NamedQuery buscarPorCodigoUnico com getSingleResult
            System.out.println("\n--- 3. @NamedQuery: Buscar por Código Único ---");
            ProdutoJpa encontrado = dao.buscarPorCodigoUnico("HDW-02");
            if (encontrado != null) {
                System.out.println("Produto encontrado: " + encontrado.getNome() + " | R$ " + encontrado.getPreco());
            }

            System.out.println("\nBuscando código inexistente (tratamento de NoResultException):");
            ProdutoJpa naoEncontrado = dao.buscarPorCodigoUnico("COD-999");
            System.out.println("Resultado da busca inexistente: " + naoEncontrado);

            // 5. Teste: @NamedQuery listarTodosOrdenados e buscarPorFaixaPreco
            System.out.println("\n--- 4. @NamedQuery: Faixa de Preço entre R$ 300 e R$ 1500 ---");
            List<ProdutoJpa> faixa = dao.buscarPorFaixaPreco(new BigDecimal("300.00"), new BigDecimal("1500.00"));
            for (ProdutoJpa p : faixa) {
                System.out.println("  -> " + p.getNome() + " | R$ " + p.getPreco());
            }

            // 6. Teste: Mutacao em massa (UPDATE) com executeUpdate
            System.out.println("\n--- 5. Mutação em Massa: Reajuste de 10% na categoria 'HDW' ---");
            em.getTransaction().begin();
            int linhasAfetadas = dao.aplicarReajustePorCategoria("HDW", new BigDecimal("1.10"));
            em.getTransaction().commit();
            System.out.println("Registros reajustados em lote: " + linhasAfetadas);

            // Conferindo valores atualizados
            em.clear();
            System.out.println("\nValores de Hardware atualizados após o reajuste:");
            List<ProdutoJpa> hardwareAtualizado = dao.buscarPorNomeCategoria("hardware");
            for (ProdutoJpa p : hardwareAtualizado) {
                System.out.println("  -> " + p.getNome() + " | Novo Preço: R$ " + p.getPreco());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.fechar();
        }
    }
}
