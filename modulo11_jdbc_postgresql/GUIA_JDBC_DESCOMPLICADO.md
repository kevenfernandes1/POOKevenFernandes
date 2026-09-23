# 🔌 Guia Descomplicado de JDBC para Iniciantes
**Java Database Connectivity (JDBC) Explicado Passo a Passo**

---

## 🎯 1. O que é o JDBC?

O **JDBC** (*Java Database Connectivity*) é a **ponte oficial** que permite a um programa escrito em **Java** se comunicar, gravar e ler dados de um **Banco de Dados Relacional** (como PostgreSQL, MySQL, Oracle, SQL Server ou SQLite).

```text
[💻 Programa Java] <---> [🔌 JDBC API (java.sql)] <---> [🚗 Driver JDBC (postgresql.jar)] <---> [🗄️ Banco PostgreSQL (SQL)]
```

> **Por que precisamos do JDBC?**
> A memória do seu programa Java (memória RAM) é **volátil**: se o programa for fechado ou o computador for desligado, todas as variáveis e listas de objetos somem. O Banco de Dados guarda as informações no **disco (persistência)** para sempre. O JDBC é o canal que leva os dados do Java para o disco.

---

## 🗣️ A Analogia do Tradutor e da Ligação Telefônica

Para entender o JDBC de forma simples, pense nele como uma conversa telefônica internacional:

| Personagem | No Mundo Real | No Desenvolvimento Java |
| :--- | :--- | :--- |
| **Você** | Fala Português e pensa em conceitos. | O **Java** (trabalha com Classes, Objetos e Métodos). |
| **O Destinatário** | Mora em outro país e só entende a língua local. | O **PostgreSQL** (só entende tabelas, linhas e comandos SQL). |
| **O Telefone + Operadora** | O meio que conecta os dois lados. | O **JDBC (`java.sql`)**. |
| **O Intérprete/Tradutor** | Converte o português para a língua do destinatário. | O **Driver JDBC (`postgresql.jar`)**. |

---

## 🧩 Os 4 Pilares Fundamentais do JDBC

Todo e qualquer código JDBC na história do Java se resume a **4 passos básicos**:

```text
 1. Conexão (Connection) ──────► 2. Comando (PreparedStatement)
                                           │
 4. Fechamento (close)   ◄────── 3. Resultado (ResultSet)
```

### 1️⃣ `Connection` (A Ligação)
É o canal de comunicação aberto entre o Java e o banco.
* Você informa a **URL de rede**, o **usuário** e a **senha**.
* Exemplo: `"jdbc:postgresql://localhost:5432/bdecommerce"`, usuário `"postgres"`.

### 2️⃣ `PreparedStatement` (A Ordem / A Pergunta)
É o comando SQL que você envia pela linha para o banco executar.
* Pode ser para **inserir** (`INSERT`), **atualizar** (`UPDATE`), **deletar** (`DELETE`) ou **consultar** (`SELECT`).
* Usamos os marcadores `?` (interrogações) para preencher valores com segurança. Isso impede ataques de **SQL Injection** (invasão por código malicioso).

### 3️⃣ `ResultSet` (A Bandeja de Respostas)
Quando você faz uma consulta (`SELECT`), o banco devolve uma tabela de resultados chamada **cursor**.
* O método `rs.next()` anda linha por linha dessa tabela.
* Você extrai os dados com métodos como `rs.getString("nome")` ou `rs.getBigDecimal("preco")` para reconstruir seus objetos Java.

### 4️⃣ `close()` (Desligar a Ligação)
Depois de usar a conexão, o comando e o resultado, você deve **fechar os recursos**.
* Se você não fechar, o banco de dados vai acumular conexões "fantasmas" abertas até travar a memória do servidor.

---

## 💻 Exemplo Prático Comentado: Salvar e Buscar

### 📥 1. Inserindo um Produto no Banco (DML - INSERT)

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.math.BigDecimal;

public class ExemploInserir {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bdecommerce";
        String user = "postgres";
        String pass = "postgres";

        String sql = "INSERT INTO produto (codigo, nome, preco, quantidade_estoque) VALUES (?, ?, ?, ?)";

        // O bloco try-with-resources fecha a conexão e o comando automaticamente ao final!
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preenchendo as interrogações '?' de forma segura:
            stmt.setString(1, "PRD100");                        // 1º '?' -> Código
            stmt.setString(2, "Teclado Mecânico RGB");          // 2º '?' -> Nome
            stmt.setBigDecimal(3, new BigDecimal("250.00"));    // 3º '?' -> Preço com precisão
            stmt.setInt(4, 15);                                 // 4º '?' -> Estoque

            // Executa o comando no banco
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Produto salvo com sucesso! Linhas inseridas: " + linhasAfetadas);

        } catch (Exception e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
        }
    }
}
```

---

### 📤 2. Buscando Dados no Banco (DQL - SELECT)

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExemploBuscar {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bdecommerce";
        String user = "postgres";
        String pass = "postgres";

        String sql = "SELECT codigo, nome, preco, quantidade_estoque FROM produto";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("--- PRODUTOS CADASTRADOS NO POSTGRESQL ---");

            // rs.next() retorna 'true' enquanto houver próximas linhas para ler
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int estoque = rs.getInt("quantidade_estoque");

                System.out.printf("[%s] %-25s | R$ %8.2f | Estoque: %d unid.%n",
                        codigo, nome, preco, estoque);
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        }
    }
}
```

---

## 🛡️ O que é Transação ACID e por que ela existe?

Imagine que você está comprando um produto no e-commerce:
1. O sistema precisa **diminuir 1 item do estoque**.
2. O sistema precisa **gerar o comprovante do pedido**.

> **E se a internet cair exatamente após diminuir o estoque, antes de gerar o pedido?** O cliente perderia o produto e a loja ficaria com o dinheiro preso!

Para resolver isso, o JDBC permite o **Controle de Transação Manual**:
* `conexao.setAutoCommit(false)`: *"Banco, não confirme nada ainda, espere eu terminar todas as etapas."*
* `conexao.commit()`: *"Tudo deu certo! Pode gravar o estoque e o pedido juntos."*
* `conexao.rollback()`: *"Deu erro em alguma etapa! Desfaça tudo o que foi feito agora para não deixar dados pela metade."*

---

## 📌 Dicionário Rápido de Termos JDBC

| Termo JDBC | O que significa? |
| :--- | :--- |
| **`Driver`** | O arquivo `.jar` que ensina o Java a conversar com um banco específico (ex.: `postgresql-42.7.4.jar`). |
| **`DriverManager`** | O gerenciador do Java que escolhe o driver certo e cria a conexão. |
| **`Connection`** | A conexão física ativa entre o Java e o SGBD. |
| **`PreparedStatement`** | Comando SQL pré-compilado e seguro com parâmetros `?`. |
| **`executeUpdate()`** | Método usado para comandos que alteram dados (`INSERT`, `UPDATE`, `DELETE`). Retorna o número de linhas afetadas. |
| **`executeQuery()`** | Método usado para consultas (`SELECT`). Retorna um `ResultSet`. |
| **`ResultSet`** | Objeto que contém a tabela com os resultados retornados pelo banco de dados. |
| **`CallableStatement`**| Interface usada para chamar rotinas pré-programadas dentro do banco (*Stored Procedures*). |
| **`SQLException`** | A exceção que o Java dispara quando qualquer coisa dá errado no banco (senha errada, tabela inexistente, etc.). |

---

## 🏆 Resumo em 1 Frase
> **JDBC é a tecnologia que transforma seu código Java em comandos que o Banco de Dados entende, garantindo que suas informações fiquem gravadas com segurança para sempre.**
