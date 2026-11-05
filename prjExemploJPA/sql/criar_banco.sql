-- Script de preparacao para o exercicio JPA
-- Executar no PostgreSQL (pgAdmin ou psql) conectado ao postgres:

CREATE DATABASE exemplo_jpa;

-- Nota: Nao e necessario criar a tabela EMPREGADO manualmente,
-- pois o Hibernate com a propriedade 'hibernate.hbm2ddl.auto' = 'update'
-- cria a tabela e suas colunas automaticamente na primeira execucao da aplicacao!
