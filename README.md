# **API de Gerenciamento de Produtos**

Este projeto é uma API RESTful para gerenciar produtos, criada usando **Spring Boot**, **Spring Data JPA** e **H2 Database**. A API permite criar, listar e deletar produtos armazenados em um banco de dados.

---

## **Sumário**
- [Pré-requisitos](#pré-requisitos)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Projeto](#configuração-do-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints](#endpoints)
  - [Listar Produtos](#listar-produtos)
  - [Criar Produto](#criar-produto)
  - [Deletar Produto](#deletar-produto)
- [Como Executar](#como-executar)


---

## **Pré-requisitos**
Antes de começar, certifique-se de ter os seguintes itens instalados:
- **Java 17 ou superior** (a versão 21 ou 23 também é compatível).
- **Maven** (caso não utilize o Maven Wrapper).
- Uma IDE como **IntelliJ IDEA** ou **VS Code** com suporte a Java.

---

## **Tecnologias Utilizadas**
- **Java 17+**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **H2 Database** (Banco de dados em memória para desenvolvimento/testes)
- **Maven** (Gerenciador de dependências)
- **Postman** (para testes da API)

---

## **Configuração do Projeto**
### Dependências no `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

```
### Banco de Dados H2
O banco de dados H2 está configurado com as seguintes propriedades:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

# Console do H2 habilitado
```
spring.h2.console.enabled=true
spring.jpa.show-sql=true
```

## Estrutura do Projeto
A estrutura do projeto é a seguinte:

```plaintext
src/main/java
└── com.exemplo.empresa.loja
    ├── controller
    │   └── ProdutoController.java       # Controlador REST
    ├── model
    │   └── Produto.java                 # Entidade do banco de dados
    ├── repository
    │   └── ProdutoRepository.java       # Interface para acesso ao banco
    ├── service
    │   └── ProdutoService.java          # Lógica de negócios
    └── LojaApplication.java             # Classe principal
```

## Endpoints da API

#### **1. Listar Produtos**
- **Descrição:** Retorna todos os produtos cadastrados no banco de dados.
- **Método:** GET
- **URL:** /api/produtos
- **Resposta de Sucesso (200 OK):**

```json

[
    {
        "id": 1,
        "nome": "Mouse Gamer",
        "preco": 150.0,
        "quantidade": 20
    },
    {
        "id": 2,
        "nome": "Teclado Mecânico",
        "preco": 350.0,
        "quantidade": 15
    }
]
```

#### **2. Criar Produto**

- **Descrição:** Adiciona um novo produto ao banco de dados.
- **Método:** POST
- **URL:** /api/produtos
- **Corpo da Requisição (JSON):**
```json

{
    "nome": "Monitor Full HD",
    "preco": 1200.00,
    "quantidade": 5
}
```
**Resposta de Sucesso (201 Created):**

```json

{
    "id": 3,
    "nome": "Monitor Full HD",
    "preco": 1200.00,
    "quantidade": 5
}
```

#### **3. Deletar Produto**
- **Descrição:** Remove um produto existente com base no seu ID.
- **Método:** DELETE
- **URL:** /api/produtos/{id}
- **Exemplo:**
- **Requisição:** DELETE /api/produtos/3
- **Resposta de Sucesso (204 No Content)**: Nenhum conteúdo retornado, indicando que o produto foi removido.
- ---

## Exemplo de Requisições no Postman
### Configuração do Postman
- Baixe e instale o Postman, disponível em: https://www.postman.com/downloads/
#### **1. Criar Produto (POST)**
- Escolha o método **POST**.
- Use a URL: `http://localhost:8080/api/produtos`
- No **Body**, insira o seguinte JSON:
```json

{
    "nome": "Cadeira Gamer",
    "preco": 800.00,
    "quantidade": 3
}
```
Clique em **Send** e verifique a resposta.
#### **2. Listar Produtos (GET)**
- o método **GET**.
- Use a URL: `http://localhost:8080/api/produtos`
- Clique em **Send** para ver a lista de produtos.
#### **3. Deletar Produto (DELETE)**
- Escolha o método **DELETE**.
- Use a URL: `http://localhost:8080/api/produtos/1`
- Substitua `1` pelo ID do produto que deseja deletar.
- Clique em **Send**.

## Testando o Banco H2
O banco de dados H2 é um banco de memória útil para testes locais. Siga os passos para verificar os dados diretamente no console do H2:

1. Acesse o console do H2 no navegador:

```bash

http://localhost:8080/h2-console
```
2. Preencha os campos:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** (deixe em branco)

3. Clique em **Connect**.

4. Execute a consulta SQL para listar os produtos:

```sql

SELECT * FROM produto;
```
5. Você verá os produtos cadastrados no banco.

---
## Como Executar
1. Certifique-se de ter Java e Maven instalados.
2. Navegue até o diretório do projeto:
```bash
cd <diretorio-do-projeto>
```

3. Execute o servidor Spring Boot:
```bash
./mvnw spring-boot:run
```
4. Acesse os endpoints via Postman ou outro cliente REST:
- **URL base:** `http://localhost:8080`
