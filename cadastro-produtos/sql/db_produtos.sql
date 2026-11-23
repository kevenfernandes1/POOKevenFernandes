-- ======================================================================
-- Laboratorio Java: Cadastro de Produtos com MVC, JPA, Hibernate e PostgreSQL
-- ======================================================================

-- Passo 1: Criacao do banco de dados (executar no pgAdmin ou terminal psql)
CREATE DATABASE db_produtos;

-- Conectar-se ao banco db_produtos antes de rodar consultas:
-- \c db_produtos

-- A tabela 'produtos' e a sequence de ID sao geradas automaticamente
-- pelo Hibernate devido a configuracao:
-- <property name="hibernate.hbm2ddl.auto" value="update"/>

-- ======================================================================
-- Passo 11: Consultas de conferencia no pgAdmin
-- ======================================================================

-- 1. Listar todos os produtos ordenados pelo ID
SELECT id, nome, quantidade, valor_unitario
FROM produtos
ORDER BY id;

-- 2. Calcular o valor total acumulado no estoque
SELECT SUM(quantidade * valor_unitario) AS total_estoque
FROM produtos;
