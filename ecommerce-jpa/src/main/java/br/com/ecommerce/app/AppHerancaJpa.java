package br.com.ecommerce.app;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.ecommerce.modelo.Frete;
import br.com.ecommerce.modelo.FreteAereo;
import br.com.ecommerce.modelo.FreteRodoviario;
import br.com.ecommerce.util.JPAUtil;

public class AppHerancaJpa {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            // 1. Instanciando fretes concretos
            FreteAereo aereo = new FreteAereo("BR-AIR-9981", new BigDecimal("150.00"), "VOO-G3-1420", new BigDecimal("45.50"));
            FreteRodoviario rodoviario = new FreteRodoviario("BR-ROD-4412", new BigDecimal("80.00"), "BRA2E19", new BigDecimal("22.80"));

            em.persist(aereo);
            em.persist(rodoviario);

            em.getTransaction().commit();
            System.out.println("Fretes persistidos com sucesso nas tabelas especificas (JOINED).");

            // 2. Consulta polimorfica via superclasse Frete
            em.clear(); // limpa o cache de 1º nivel para carregar do banco

            System.out.println("\n--- Consulta Polimorfica: SELECT f FROM Frete f ---");
            List<Frete> listaFretes = em.createQuery("SELECT f FROM Frete f", Frete.class).getResultList();

            for (Frete f : listaFretes) {
                System.out.println("\nRegistro ID: " + f.getId() + " | Rastreio: " + f.getCodigoRastreio());
                System.out.println("Valor Base: R$ " + f.getValorBase());

                // Inspecao polimorfica com instanceof
                if (f instanceof FreteAereo) {
                    FreteAereo fa = (FreteAereo) f;
                    System.out.println("Modalidade: Aérea | Voo: " + fa.getPrefixoVoo() + 
                                       " | Taxa Aeroportuária: R$ " + fa.getTaxaAeroportuaria());
                } else if (f instanceof FreteRodoviario) {
                    FreteRodoviario fr = (FreteRodoviario) f;
                    System.out.println("Modalidade: Rodoviária | Placa: " + fr.getPlacaVeiculo() + 
                                       " | Pedágios: R$ " + fr.getValorPedagios());
                }

                // Invocacao polimorfica do metodo abstrato
                System.out.println("Custo Total Calculado: R$ " + f.calcularCustoTotal());
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
