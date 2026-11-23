package br.com.ecommerce.app;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import br.com.ecommerce.modelo.ProdutoJpa;
import br.com.ecommerce.util.JPAUtil;

public class CicloVidaJpaApp {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            // 1. Estado Transient / New (objeto em memoria, sem ID e nao gerenciado)
            System.out.println("--- 1. Estado Transient / New ---");
            ProdutoJpa produto = new ProdutoJpa("PRD-101", "Teclado Mecanico RGB", new BigDecimal("250.00"), 20);
            System.out.println("Objeto instanciado: " + produto);
            System.out.println("ID antes do persist: " + produto.getId());

            // 2. Transicao para Managed (persist e flush)
            System.out.println("\n--- 2. Estado Managed (persist + flush) ---");
            em.getTransaction().begin();
            em.persist(produto);
            em.flush(); // Forca o envio do INSERT para o banco gerando a chave primaria
            System.out.println("Objeto apos persist e flush: " + produto);
            System.out.println("ID gerado pelo banco: " + produto.getId());

            // 3. Dirty Checking (sincronizacao automatica ao comitar)
            System.out.println("\n--- 3. Dirty Checking ---");
            produto.setPreco(new BigDecimal("229.90"));
            produto.setQuantidadeEstoque(25);
            em.getTransaction().commit(); // O Hibernate detecta a alteracao e gera UPDATE automaticamente
            System.out.println("Transacao confirmada com alteracoes sincronizadas.");

            // 4. Transicao para Detached
            System.out.println("\n--- 4. Estado Detached (detach e merge) ---");
            em.detach(produto);
            produto.setNome("Teclado Mecanico RGB Pro (Desconectado)");
            System.out.println("Nome alterado localmente no objeto desconectado.");

            // Religar com merge
            em.getTransaction().begin();
            ProdutoJpa produtoReligado = em.merge(produto);
            em.getTransaction().commit();
            System.out.println("Objeto religado via merge: " + produtoReligado.getNome());

            // 5. Metodo refresh (sobrepoem alteracoes locais com os dados do banco)
            System.out.println("\n--- 5. Metodo refresh ---");
            produtoReligado.setPreco(new BigDecimal("9999.00")); // alteracao volatil apenas na memoria
            System.out.println("Preco adulterado na memoria: R$ " + produtoReligado.getPreco());
            em.refresh(produtoReligado);
            System.out.println("Preco restaurado do banco com refresh(): R$ " + produtoReligado.getPreco());

            // 6. Transicao para Removed
            System.out.println("\n--- 6. Estado Removed ---");
            em.getTransaction().begin();
            ProdutoJpa produtoParaRemover = em.find(ProdutoJpa.class, produtoReligado.getId());
            em.remove(produtoParaRemover);
            em.getTransaction().commit();
            System.out.println("Registro removido do banco com sucesso.");

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
