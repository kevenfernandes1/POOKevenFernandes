# Repositório de Programação Orientada a Objetos (POO)

Repositório acadêmico contendo as resoluções práticas das listas de exercícios e projetos integradores da disciplina de Programação Orientada a Objetos.

---

## 📁 Estrutura Geral do Repositório

```text
POOKevenFernandes/
├── .gitignore
├── README.md
│
├── lista1_excecoes/
│   ├── exercicio1/
│   │   └── DivisaoSegura.java
│   ├── exercicio2/
│   │   └── AcessoArray.java
│   ├── exercicio3/
│   │   ├── IdadeInvalidaException.java
│   │   └── ValidacaoIdade.java
│   ├── exercicio4/
│   │   └── ConversorNumerico.java
│   └── exercicio5/
│       ├── SaldoInsuficienteException.java
│       ├── ContaBancaria.java
│       └── TesteContaBancaria.java
│
├── lista2_colecoes_associacoes/
│   ├── nivel1/
│   │   ├── Produto.java
│   │   └── MainCadastro.java
│   ├── nivel2/
│   │   ├── exercicio2_1/
│   │   │   ├── Produto.java
│   │   │   ├── Categoria.java
│   │   │   └── MainCategoria.java
│   │   └── exercicio2_2/
│   │       ├── Produto.java
│   │       ├── Fabricante.java
│   │       └── MainFabricante.java
│   ├── nivel3/
│   │   ├── Produto.java
│   │   ├── Item.java
│   │   ├── NotaFiscal.java
│   │   └── MainNotaFiscal.java
│   └── nivel4/
│       ├── Produto.java
│       ├── ComparadorPorPreco.java
│       ├── ComparadorPorCodigo.java
│       └── MainOrdenacao.java
│
└── lista3_colecoes_arquitetura/
    ├── missao1/
    │   ├── Rastreavel.java
    │   ├── PacoteCorreios.java
    │   ├── CargaTransportadora.java
    │   ├── EntregaExpressa.java
    │   ├── CentralRastreamento.java
    │   └── MainMissao1.java
    ├── missao2/
    │   ├── GerenciadorFilas.java
    │   ├── RegistradorLogsVector.java
    │   └── MainMissao2.java
    ├── missao3/
    │   ├── CupomDesconto.java
    │   ├── GerenciadorCupons.java
    │   └── MainMissao3.java
    ├── missao4/
    │   ├── Produto.java
    │   ├── ComparadorPorPreco.java
    │   └── MainMissao4.java
    └── missao5/
        ├── Produto.java
        ├── ControleEstoquePDV.java
        └── MainMissao5.java
```

---

## 📌 Lista 1: Tratamento de Exceções em Java

1. **Divisão Segura (`exercicio1`)**: Divisão entre números com tratamento de `ArithmeticException`, `InputMismatchException` e bloco `finally`.
2. **Acesso a Posições de Array (`exercicio2`)**: Consulta em array com captura de `ArrayIndexOutOfBoundsException`.
3. **Validação de Idade (`exercicio3`)**: Exceção personalizada não-checada (`IdadeInvalidaException` herdando de `RuntimeException`).
4. **Conversor Numérico com Propagação (`exercicio4`)**: Método com `throws NumberFormatException` e captura com `try-catch`.
5. **Cadastro de Conta Bancária (`exercicio5`)**: Controle de saldo com exceção checada customizada `SaldoInsuficienteException`.

---

## 📌 Lista 2: Coleções (ArrayList) e Associações em Java

* **Nível 1 — Manipulação Dinâmica com ArrayList**: Inserção, busca linear (`buscarPorCodigo`), remoção (por índice e objeto) e atualização em lote (`aplicarReajusteGeral`).
* **Nível 2 — Associações 1:N e Consistência Bidirecional**: Agregação unidirecional (`Categoria` -> `Produto`) e integridade referencial bidirecional com prevenção de loop infinito (`Produto` <-> `Fabricante`).
* **Nível 3 — Classes de Associação (N:M)**: Decomposição de muitos-para-muitos via classe associativa `Item` entre `NotaFiscal` e `Produto`.
* **Nível 4 — Ordenação e Contratos de Interface**: Ordenação natural com `Comparable<Produto>` e ordenações customizadas com `Comparator<Produto>`.

---

## 📌 Lista 3: Coleções e Arquitetura em Java (Projeto Integrador Logística & E-Commerce)

### Missão 1: Contratos de Serviço e Validação de Tipos
- **Interface `Rastreavel`**: Desacoplamento de modalidades logísticas (`PacoteCorreios`, `CargaTransportadora`, `EntregaExpressa`).
- **`CentralRastreamento`**: Inspeção polimórfica via operador `instanceof` e downcasting explícito seguro.

### Missão 2: Filas de Processamento, Listas e Concorrência
- **`LinkedList` vs `ArrayList`**: Uso da `LinkedList` para inserção nas extremidades (`O(1)`) em filas de pedidos pendentes e VIPs.
- **`Vector` Concorrente**: Conceito de métodos `synchronized` para registro de auditoria thread-safe em cenários multithread.

### Missão 3: Prevenção de Duplicidades e Navegação Segura
- **`HashSet` & Contratos `equals`/`hashCode`**: Unicidade estrita de cupons promocionais baseada exclusivamente no código.
- **Padrão `Iterator`**: Remoção segura de cupons zerados via `it.remove()`, evitando `ConcurrentModificationException`.

### Missão 4: Classificação Automática e Algoritmos de Ordenação
- **`TreeSet` & Red-Black Tree**: Auto-ordenação com custo $O(\log n)$.
- **`Comparable` & `Comparator`**: Comparação natural por nome e ranking por preço com desempate rigoroso.

### Missão 5: Acesso Direto O(1) e Indexação por Chave
- **`HashMap` no Ponto de Venda (PDV)**: Indexação por código de barras e recuperação direta em $O(1)$ sem laços de repetição.
- **Visões de Coleção**: Exploração de `keySet()` ($Set$) e `values()` ($Collection$).

---

## 🚀 Como Compilar e Executar

Na raiz do repositório:

```bash
# Compilar todo o projeto
javac $(find . -name "*.java")

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

# --- LISTA 3: COLEÇÕES E ARQUITETURA (PROJETO INTEGRADOR) ---
java lista3_colecoes_arquitetura.missao1.MainMissao1
java lista3_colecoes_arquitetura.missao2.MainMissao2
java lista3_colecoes_arquitetura.missao3.MainMissao3
java lista3_colecoes_arquitetura.missao4.MainMissao4
java lista3_colecoes_arquitetura.missao5.MainMissao5
```
