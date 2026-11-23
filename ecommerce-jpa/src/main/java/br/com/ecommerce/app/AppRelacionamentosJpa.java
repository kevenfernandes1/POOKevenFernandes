package br.com.ecommerce.app;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import br.com.ecommerce.modelo.Categoria;
import br.com.ecommerce.modelo.Cliente;
import br.com.ecommerce.modelo.PedidoJpa;
import br.com.ecommerce.modelo.PerfilFiscal;
import br.com.ecommerce.modelo.ProdutoJpa;
import br.com.ecommerce.util.JPAUtil;

public class AppRelacionamentosJpa {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            // 1. Teste de Relacionamento 1:1 (Cliente e PerfilFiscal com CascadeType.ALL)
            System.out.println("--- 1. Relacionamento 1:1 (Cliente e PerfilFiscal) ---");
            PerfilFiscal fiscal = new PerfilFiscal("12.345.678/0001-90", "123456789", "Lucro Presumido");
            Cliente cliente = new Cliente("Tech Comércio Ltda", "contato@techcomercio.com", fiscal);

            // Persiste apenas o cliente; o PerfilFiscal e salvo automaticamente em cascata
            em.persist(cliente);
            System.out.println("Cliente e PerfilFiscal persistidos via CascadeType.ALL:");
            System.out.println("Cliente ID: " + cliente.getId() + " | Perfil Fiscal ID: " + fiscal.getId());

            // 2. Teste de Relacionamento 1:N (Categoria e ProdutoJpa com CascadeType.ALL, @OrderBy e @MapKey)
            System.out.println("\n--- 2. Relacionamento 1:N (Categoria e Produtos) ---");
            Categoria catPerifericos = new Categoria("Periféricos", "PERIF");

            ProdutoJpa p1 = new ProdutoJpa("P-003", "Mouse Gamer 16000 DPI", new BigDecimal("180.00"), 30);
            ProdutoJpa p2 = new ProdutoJpa("P-001", "Headset Surround 7.1", new BigDecimal("320.00"), 15);
            ProdutoJpa p3 = new ProdutoJpa("P-002", "Mousepad Speed Extra Grande", new BigDecimal("75.00"), 50);

            catPerifericos.adicionarProduto(p1);
            catPerifericos.adicionarProduto(p2);
            catPerifericos.adicionarProduto(p3);

            // Persiste a categoria; os 3 produtos sao persistidos em cascata
            em.persist(catPerifericos);

            // 3. Teste de Relacionamento N:N (PedidoJpa e ProdutoJpa)
            System.out.println("\n--- 3. Relacionamento N:N (PedidoJpa e Produtos) ---");
            PedidoJpa pedido = new PedidoJpa("PED-2026-001");
            pedido.adicionarProduto(p1);
            pedido.adicionarProduto(p2);

            em.persist(pedido);

            em.getTransaction().commit();

            // 4. Verificacao das colecoes em nova sessao
            em.clear(); // limpa o cache de 1º nivel para forcar consulta ao banco

            System.out.println("\n--- Verificando carregamento no banco ---");
            Categoria catConsultada = em.find(Categoria.class, catPerifericos.getId());
            System.out.println("Categoria recuperada: " + catConsultada.getNome());

            // Produtos ordenados pelo nome conforme @OrderBy("nome ASC")
            System.out.println("\nProdutos auto-ordenados por nome (@OrderBy):");
            for (ProdutoJpa prod : catConsultada.getProdutos()) {
                System.out.println("  -> " + prod.getNome() + " | R$ " + prod.getPreco());
            }

            // Acesso direto em memoria pelo mapa indexado por codigo (@MapKey(name = "codigo"))
            System.out.println("\nRecuperacao direta via Map indexado por codigo (@MapKey):");
            ProdutoJpa prodBuscado = catConsultada.getProdutosPorCodigo().get("P-001");
            System.out.println("Produto com codigo P-001 no Map: " + prodBuscado.getNome());

            // Pedido e itens associados
            PedidoJpa pedidoConsultado = em.find(PedidoJpa.class, pedido.getId());
            System.out.println("\nPedido: " + pedidoConsultado.getNumeroPedido() + " - Itens vinculados:");
            for (ProdutoJpa item : pedidoConsultado.getProdutos()) {
                System.out.println("  -> " + item.getNome() + " (" + item.getCodigo() + ")");
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
