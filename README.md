# Repositório de Programação Orientada a Objetos (POO)

Repositório acadêmico contendo as resoluções práticas das listas de exercícios e projetos integradores da disciplina de Programação Orientada a Objetos.

---

## 📁 Estrutura Geral do Repositório

```text
POOKevenFernandes/
├── .gitignore
├── README.md
├── lib/
│   └── postgresql-42.7.4.jar
│
├── lista1_excecoes/
│   ├── exercicio1/ (DivisaoSegura.java)
│   ├── exercicio2/ (AcessoArray.java)
│   ├── exercicio3/ (IdadeInvalidaException.java, ValidacaoIdade.java)
│   ├── exercicio4/ (ConversorNumerico.java)
│   └── exercicio5/ (SaldoInsuficienteException.java, ContaBancaria.java, TesteContaBancaria.java)
│
├── lista2_colecoes_associacoes/
│   ├── nivel1/ (Produto.java, MainCadastro.java)
│   ├── nivel2/
│   │   ├── exercicio2_1/ (Produto.java, Categoria.java, MainCategoria.java)
│   │   └── exercicio2_2/ (Produto.java, Fabricante.java, MainFabricante.java)
│   ├── nivel3/ (Produto.java, Item.java, NotaFiscal.java, MainNotaFiscal.java)
│   └── nivel4/ (Produto.java, ComparadorPorPreco.java, ComparadorPorCodigo.java, MainOrdenacao.java)
│
├── lista3_colecoes_arquitetura/
│   ├── missao1/ (Rastreavel.java, PacoteCorreios.java, CargaTransportadora.java, EntregaExpressa.java, CentralRastreamento.java, MainMissao1.java)
│   ├── missao2/ (GerenciadorFilas.java, RegistradorLogsVector.java, MainMissao2.java)
│   ├── missao3/ (CupomDesconto.java, GerenciadorCupons.java, MainMissao3.java)
│   ├── missao4/ (Produto.java, ComparadorPorPreco.java, MainMissao4.java)
│   └── missao5/ (Produto.java, ControleEstoquePDV.java, MainMissao5.java)
│
├── lista4_topicos_avancados/
│   ├── Produto.java
│   ├── GerenciadorSubconjuntos.java
│   ├── FilaPedidosQueue.java
│   ├── UtilitariosCollections.java
│   ├── SessoesHashtable.java
│   └── MainAvancado.java
│
└── modulo11_jdbc_postgresql/
    ├── sql/
    │   └── schema_ecommerce.sql
    └── br/com/ecommerce/
        ├── model/
        │   ├── Produto.java
        │   └── Pedido.java
        └── jdbc/
            ├── FabricaConexao.java
            ├── TesteConexaoPostgreSQL.java
            ├── ProdutoDAO.java
            ├── ServicoVendaTransacional.java
            ├── RecursosAvancadosDAO.java
            └── MainJDBCIntegrador.java
```

---

## 📌 Resumo dos Módulos

### 1. Tratamento de Exceções (`lista1_excecoes`)
- Exceções padrão (`ArithmeticException`, `InputMismatchException`, `ArrayIndexOutOfBoundsException`, `NumberFormatException`).
- Exceções customizadas checadas (`SaldoInsuficienteException`) e não-checadas (`IdadeInvalidaException`).

### 2. Coleções e Associações (`lista2_colecoes_associacoes`)
- Manipulação dinâmica com `ArrayList`.
- Associações 1:N unidirecionais e bidirecionais com integridade referencial mútua.
- Classes de associação N:M (`Item` ligando `NotaFiscal` e `Produto`).
- Ordenação com `Comparable` e `Comparator`.

### 3. Coleções e Arquitetura (`lista3_colecoes_arquitetura`)
- Polimorfismo, `instanceof` e downcasting explícito.
- Filas com `LinkedList` ($O(1)$) e concorrência multithread com `Vector` (`synchronized`).
- Unicidade no `HashSet` (`equals`/`hashCode`) e higienização segura com `Iterator.remove()`.
- Auto-ordenação com `TreeSet` e acesso direto $O(1)$ com `HashMap`.

### 4. Exercício Complementar Avançado (`lista4_topicos_avancados`)
- Subconjuntos do `SortedSet` (`subSet`, `headSet`, `tailSet`).
- Contrato formal `Queue` com `offer()`, `peek()` e `poll()`.
- Utilitários de `Collections` (`shuffle`, `reverse`, `min`, `max`, `synchronizedList`).
- Estrutura legada `Hashtable` e percorrimento via `Enumeration`.

### 5. Persistência Relacional com JDBC e PostgreSQL (`modulo11_jdbc_postgresql`)
- **Nível 1 (Arquitetura e Conexão)**: `FabricaConexao` com `Class.forName("org.postgresql.Driver")`, `DriverManager.getConnection()`, tratamento rigoroso de `ClassNotFoundException` e `SQLException`, e comprovação do polimorfismo da interface `java.sql.Connection`.
- **Nível 2 (DML Seguro e SQL Injection)**: `ProdutoDAO` utilizando `PreparedStatement` parametrizado (`?`), mapeamento de precisão monetária com `BigDecimal` (`NUMERIC`) e validação de linhas afetadas via `executeUpdate()`.
- **Nível 3 (Consultas e Cursores)**: Navegação de `ResultSet` com extração posicional e nomeada, mapeamento objeto-relacional (ORM) e encerramento de recursos em cascata (`rs.close()`, `stmt.close()`, `conn.close()`).
- **Nível 4 (Integridade Transacional ACID)**: `ServicoVendaTransacional` com controle manual `setAutoCommit(false)`, verificação de estoque, débito e registro de pedido atômicos, com `commit()` e tratamento de `rollback()` em falhas.
- **Nível 5 (Recursos Avançados)**: `RecursosAvancadosDAO` com cursor rolável `TYPE_SCROLL_INSENSITIVE` (`first`, `last`, `previous`, `absolute`) e execução de Stored Procedure corporativa via `CallableStatement` com parâmetros `IN` e `OUT` (`registerOutParameter`).

---

## 🚀 Como Compilar e Executar

Na raiz do repositório:

### 1. Compilar Todo o Projeto
```bash
javac -cp "lib/postgresql-42.7.4.jar;." $(find . -name "*.java")
```

### 2. Executar os Módulos

```bash
# --- LISTA 1: EXCEÇÕES ---
java lista1_excecoes.exercicio1.DivisaoSegura
java lista1_excecoes.exercicio2.AcessoArray
java lista1_excecoes.exercicio3.ValidacaoIdade
java lista1_excecoes.exercicio4.ConversorNumerico
java lista1_excecoes.exercicio5.TesteContaBancaria

# --- LISTA 2: COLEÇÕES E ASSOCIAÇÕES ---
java lista2_colecoes_associacoes.nivel1.MainCadastro
java lista2_colecoes_associacoes.nivel2.exercicio2_1.MainCategoria
java lista2_colecoes_associacoes.nivel2.exercicio2_2.MainFabricante
java lista2_colecoes_associacoes.nivel3.MainNotaFiscal
java lista2_colecoes_associacoes.nivel4.MainOrdenacao

# --- LISTA 3: COLEÇÕES E ARQUITETURA ---
java lista3_colecoes_arquitetura.missao1.MainMissao1
java lista3_colecoes_arquitetura.missao2.MainMissao2
java lista3_colecoes_arquitetura.missao3.MainMissao3
java lista3_colecoes_arquitetura.missao4.MainMissao4
java lista3_colecoes_arquitetura.missao5.MainMissao5

# --- LISTA 4: TÓPICOS AVANÇADOS ---
java lista4_topicos_avancados.MainAvancado

# --- MÓDULO 11: JDBC & POSTGRESQL ---
java -cp "lib/postgresql-42.7.4.jar;modulo11_jdbc_postgresql;." br.com.ecommerce.jdbc.TesteConexaoPostgreSQL
java -cp "lib/postgresql-42.7.4.jar;modulo11_jdbc_postgresql;." br.com.ecommerce.jdbc.MainJDBCIntegrador
```
