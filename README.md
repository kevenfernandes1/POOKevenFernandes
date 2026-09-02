# POO - Lista de Exercícios sobre Exceções em Java

Repositório contendo a resolução dos exercícios práticos sobre tratamento de exceções em Java (Programação Orientada a Objetos).

---

## 📁 Estrutura do Projeto

```text
POOKevenFernandes/
├── .gitignore
├── README.md
├── exercicio1/
│   └── DivisaoSegura.java
├── exercicio2/
│   └── AcessoArray.java
├── exercicio3/
│   ├── IdadeInvalidaException.java
│   └── ValidacaoIdade.java
├── exercicio4/
│   └── ConversorNumerico.java
└── exercicio5/
    ├── SaldoInsuficienteException.java
    ├── ContaBancaria.java
    └── TesteContaBancaria.java
```

---

## 📝 Descrição dos Exercícios

### 1. Divisão Segura (`exercicio1`)
- **Objetivo**: Ler dois números inteiros e realizar a divisão.
- **Tratamentos**:
  - `ArithmeticException`: divisão por zero.
  - `InputMismatchException`: entrada de dados que não sejam números inteiros.
  - Bloco `finally`: exibição da mensagem `"Fim da operação"`.

### 2. Acesso a Posições de Array (`exercicio2`)
- **Objetivo**: Acessar um array de 5 cidades via índice digitado pelo usuário.
- **Tratamentos**:
  - `ArrayIndexOutOfBoundsException`: acesso a posições inexistentes (< 0 ou >= 5).

### 3. Validação de Idade (`exercicio3`)
- **Objetivo**: Validar a idade informada pelo usuário.
- **Recursos**:
  - Exceção personalizada `IdadeInvalidaException` (herda de `RuntimeException`).
  - Lançamento de exceção para idades menores que 0 ou maiores que 150.
  - Captura e exibição no método `main`.

### 4. Conversor Numérico com Propagação (`exercicio4`)
- **Objetivo**: Converter uma String em número inteiro.
- **Recursos**:
  - Método `converterParaInteiro(String texto)` com cláusula `throws NumberFormatException`.
  - Tratamento no método `main` com `try-catch`.

### 5. Cadastro de Conta Bancária (`exercicio5`)
- **Objetivo**: Simular operações bancárias com validação de saldo.
- **Recursos**:
  - Classe `ContaBancaria` com atributo `saldo` e método `sacar(double valor)`.
  - Exceção personalizada `SaldoInsuficienteException`.
  - Lançamento de exceção caso o valor do saque seja superior ao saldo.
  - Classe `TesteContaBancaria` demonstrando um saque com sucesso e outro com saldo insuficiente.

---

## 🚀 Como Executar

Abra o terminal na pasta raiz do projeto e execute os comandos de compilação e execução:

```bash
# Exercício 1
javac exercicio1/DivisaoSegura.java
java exercicio1.DivisaoSegura

# Exercício 2
javac exercicio2/AcessoArray.java
java exercicio2.AcessoArray

# Exercício 3
javac exercicio3/*.java
java exercicio3.ValidacaoIdade

# Exercício 4
javac exercicio4/ConversorNumerico.java
java exercicio4.ConversorNumerico

# Exercício 5
javac exercicio5/*.java
java exercicio5.TesteContaBancaria
```
