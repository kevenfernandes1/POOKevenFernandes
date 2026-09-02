# Repositório de Programação Orientada a Objetos (POO)

Repositório acadêmico contendo as resoluções práticas das listas de exercícios da disciplina de Programação Orientada a Objetos.

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
└── lista2_colecoes_associacoes/
    ├── nivel1/
    │   ├── Produto.java
    │   └── MainCadastro.java
    ├── nivel2/
    │   ├── exercicio2_1/
    │   │   ├── Produto.java
    │   │   ├── Categoria.java
    │   │   └── MainCategoria.java
    │   └── exercicio2_2/
    │       ├── Produto.java
    │       ├── Fabricante.java
    │       └── MainFabricante.java
    ├── nivel3/
    │   ├── Produto.java
    │   ├── Item.java
    │   ├── NotaFiscal.java
    │   └── MainNotaFiscal.java
    └── nivel4/
        ├── Produto.java
        ├── ComparadorPorPreco.java
        ├── ComparadorPorCodigo.java
        └── MainOrdenacao.java
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

### Nível 1: Introdução ao ArrayList e Manipulação Dinâmica
- **[Exercício 1.1] Cadastro Básico de Produtos**:
  - Encapsulamento rigoroso da classe `Produto`.
  - Métodos `add()`, `size()`, `get()`, `remove()` (por índice e por objeto).
- **[Exercício 1.2] Busca e Atualização com Iteração**:
  - `buscarPorCodigo()` para busca linear e tratamento de elemento inexistente.
  - `aplicarReajusteGeral()` para modificação em massa dos preços via percentual.

### Nível 2: Associações Simples (1:N) e Consistência Bidirecional
- **[Exercício 2.1] Associação Unidirecional (Categoria 1 -> 0..* Produto)**:
  - Agregação de produtos na classe `Categoria` via `ArrayList<Produto>`.
- **[Exercício 2.2] Associação Bidirecional Consistente (Produto * <-> 1 Fabricante)**:
  - Garantia de integridade referencial mútua entre `Produto` e `Fabricante`.
  - Sincronização automática nos métodos `setFabricante()`, `addProduto()` e `removeProduto()`, prevenindo loops infinitos.

### Nível 3: Relacionamentos N:M e Classes de Associação
- **[Exercício 3.1] Decomposição de Relacionamento N:M (Nota Fiscal & Produtos via Item)**:
  - Classe de associação `Item` intermediando `NotaFiscal` e `Produto`.
  - Cálculo de subtotal por item e soma total dinâmica da nota fiscal.

### Nível 4: Ordenação e Contratos de Interface (Comparable e Comparator)
- **[Exercício 4.1] Ordenação Natural (`Comparable`)**:
  - Implementação de `Comparable<Produto>` com ordenação alfabética por nome (`compareTo`).
- **[Exercício 4.2] Critérios de Ordenação Alternativos (`Comparator`)**:
  - Ordenação por preço crescente (`ComparadorPorPreco`).
  - Ordenação por preço decrescente (`Collections.reverseOrder()`).
  - Ordenação alfabética por código (`ComparadorPorCodigo`).

---

## 🚀 Como Compilar e Executar

Na raiz do projeto:

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
```
