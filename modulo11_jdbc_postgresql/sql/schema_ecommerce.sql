-- ======================================================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS E TABELAS DO E-COMMERCE (POSTGRESQL)
-- Disciplina: Programação Orientada a Objetos | Módulo 11: JDBC
-- ======================================================================

-- 1. Criação do Banco de Dados (executar conectado como superusuário postgres)
-- CREATE DATABASE bdecommerce;

-- Conecte-se ao banco bdecommerce antes de executar os comandos abaixo:
-- \c bdecommerce;

-- 2. Tabela de Catálogo de Produtos
CREATE TABLE IF NOT EXISTS produto (
    codigo VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    quantidade_estoque INTEGER DEFAULT 0
);

-- 3. Tabela de Pedidos Realizados
CREATE TABLE IF NOT EXISTS pedido (
    id_pedido VARCHAR(20) PRIMARY KEY,
    codigo_produto VARCHAR(10) NOT NULL,
    quantidade_comprada INTEGER NOT NULL,
    data_pedido TIMESTAMP NOT NULL,
    CONSTRAINT fk_pedido_produto FOREIGN KEY (codigo_produto) REFERENCES produto (codigo)
);

-- 4. Stored Procedure Corporativa: Cálculo de Saldo e Valor Patrimonial em Estoque
CREATE OR REPLACE PROCEDURE sp_calcular_saldo_estoque(
    IN p_codigo VARCHAR,
    OUT p_quantidade INT,
    OUT p_total_reais NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT quantidade_estoque, (quantidade_estoque * preco)
    INTO p_quantidade, p_total_reais
    FROM produto
    WHERE codigo = p_codigo;
END;
$$;

-- 5. Carga Inicial de Dados para Testes Práticos
INSERT INTO produto (codigo, nome, preco, quantidade_estoque) VALUES
('PRD001', 'Smartphone Samsung Galaxy S24', 4500.00, 15),
('PRD002', 'Monitor LG UltraWide 29', 1250.00, 8),
('PRD003', 'Teclado Mecânico RGB Redragon', 280.00, 30),
('PRD004', 'Mouse Gamer Logitech G Pro', 390.00, 20),
('PRD005', 'Notebook Dell G15 Core i7', 6200.00, 5)
ON CONFLICT (codigo) DO NOTHING;
