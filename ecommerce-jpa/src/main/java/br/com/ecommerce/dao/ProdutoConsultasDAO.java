package br.com.ecommerce.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import br.com.ecommerce.modelo.ProdutoJpa;

public class ProdutoConsultasDAO {

    private final EntityManager em;

    public ProdutoConsultasDAO(EntityManager em) {
        this.em = em;
    }

    // 1. JPQL dinamica com parametro nomeado :teto
    public List<ProdutoJpa> listarPorPrecoMaximo(BigDecimal teto) {
        String jpql = "SELECT p FROM ProdutoJpa p WHERE p.preco <= :teto ORDER BY p.preco ASC";
        TypedQuery<ProdutoJpa> query = em.createQuery(jpql, ProdutoJpa.class);
        query.setParameter("teto", teto);
        return query.getResultList();
    }

    // 2. Navegacao por relacionamento orientado a objetos
    public List<ProdutoJpa> buscarPorNomeCategoria(String nomeCat) {
        String jpql = "SELECT p FROM ProdutoJpa p WHERE LOWER(p.categoria.nome) LIKE :nomeCat";
        TypedQuery<ProdutoJpa> query = em.createQuery(jpql, ProdutoJpa.class);
        query.setParameter("nomeCat", "%" + nomeCat.toLowerCase() + "%");
        return query.getResultList();
    }

    // 3. Execucao de @NamedQuery com getSingleResult e tratamento de excecoes
    public ProdutoJpa buscarPorCodigoUnico(String codigo) {
        try {
            TypedQuery<ProdutoJpa> query = em.createNamedQuery("ProdutoJpa.buscarPorCodigoUnico", ProdutoJpa.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aviso: Nenhum produto encontrado com o codigo " + codigo);
            return null;
        } catch (NonUniqueResultException e) {
            System.err.println("Erro: Mais de um produto encontrado com o codigo " + codigo);
            return null;
        }
    }

    // NamedQuery para listar todos ordenados
    public List<ProdutoJpa> listarTodosOrdenados() {
        TypedQuery<ProdutoJpa> query = em.createNamedQuery("ProdutoJpa.listarTodosOrdenados", ProdutoJpa.class);
        return query.getResultList();
    }

    // NamedQuery para faixa de precos
    public List<ProdutoJpa> buscarPorFaixaPreco(BigDecimal min, BigDecimal max) {
        TypedQuery<ProdutoJpa> query = em.createNamedQuery("ProdutoJpa.buscarPorFaixaPreco", ProdutoJpa.class);
        query.setParameter("precoMinimo", min);
        query.setParameter("precoMaximo", max);
        return query.getResultList();
    }

    // 4. Mutacao em massa (UPDATE) com executeUpdate
    public int aplicarReajustePorCategoria(String siglaCategoria, BigDecimal multiplicador) {
        String jpql = "UPDATE ProdutoJpa p SET p.preco = p.preco * :multiplicador WHERE p.categoria.sigla = :sigla";
        Query query = em.createQuery(jpql);
        query.setParameter("multiplicador", multiplicador);
        query.setParameter("sigla", siglaCategoria);
        return query.executeUpdate();
    }
}
