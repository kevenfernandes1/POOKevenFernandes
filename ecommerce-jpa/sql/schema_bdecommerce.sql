-- ======================================================================
-- Modulo 12: Persistencia Avancada com JPA e Hibernate
-- Projeto Integrador: Sistema de Gestao Logistica e E-Commerce
-- ======================================================================

-- 1. Criacao do banco de dados (executar no PostgreSQL / pgAdmin conectado ao postgres):
CREATE DATABASE bdecommerce;

-- Conectar-se ao banco bdecommerce antes de rodar consultas de conferencia:
-- \c bdecommerce

-- NOTA ARQUITETURAL:
-- Nao e necessario criar as tabelas manualmente por script DDL.
-- Gracas a configuracao '<property name="hibernate.hbm2ddl.auto" value="update" />',
-- o Hibernate gera e sincroniza automaticamente todo o esquema relacional:
--   - Tabelas basicas: CLIENTE, PERFIL_FISCAL, CATEGORIA, PRODUTO_JPA, PEDIDO_JPA
--   - Tabela associativa N:N: ITEM_PEDIDO_ASSOCIACAO
--   - Estrategia JOINED de heranca: FRETE, FRETE_AEREO, FRETE_RODOVIARIO

-- ======================================================================
-- Consultas de conferencia no pgAdmin
-- ======================================================================

-- Conferir produtos e categorias associadas
SELECT p.id, p.codigo, p.nome, p.preco, p.quantidade_estoque, c.nome AS categoria
FROM produto_jpa p
LEFT JOIN categoria c ON p.id_categoria = c.id
ORDER BY p.id;

-- Conferir clientes e perfis fiscais (1:1)
SELECT cl.id, cl.nome, cl.email, pf.cnpj_cpf, pf.regime_tributario
FROM cliente cl
LEFT JOIN perfil_fiscal pf ON cl.id_perfil_fiscal = pf.id;

-- Conferir pedidos e associacao de itens (N:N)
SELECT ped.numero_pedido, prd.codigo, prd.nome, prd.preco
FROM pedido_jpa ped
JOIN item_pedido_associacao ipa ON ped.id = ipa.id_pedido
JOIN produto_jpa prd ON ipa.id_produto = prd.id;

-- Conferir heranca JOINED de fretes
SELECT f.id, f.codigo_rastreio, f.valor_base,
       fa.prefixo_voo, fa.taxa_aeroportuaria,
       fr.placa_veiculo, fr.valor_pedagios
FROM frete f
LEFT JOIN frete_aereo fa ON f.id = fa.id_frete
LEFT JOIN frete_rodoviario fr ON f.id = fr.id_frete;
