
# DevCineLocadora 🎬🍿

## O que é?

DevCineLocadora é uma API REST feita em Java com Spring Boot, criada para simular uma locadora de filmes clássica. Você pode cadastrar clientes, filmes, fazer alugueis, listar tudo, atualizar e deletar.  

Tudo isso de um jeito organizado, com validações, tratamento de exceções personalizadas e documentação Swagger pra ficar tudo fácil de usar.  

Esse projeto é perfeito pra quem quer aprender a montar uma API REST organizada e com funcionalidades bacanas — além de ser um portfólio show de bola!

---

## O que tem dentro?

- **Java 17** + Spring Boot (com Lombok pra facilitar)
- Camadas bem definidas: Entities, DTOs, Services, Controllers, Exceptions
- Validações usando Bean Validation (JSR-380)
- Tratamento de exceções customizadas com mensagens claras
- Swagger UI configurado para documentação API
- Banco MySQL com scripts SQL para popular a base com:
  - 50 filmes famosos (estoque até 4)
  - 20 clientes simulados
  - 20 alugueis simulados

- Configurações no `application.properties` para conectar ao MySQL
- Scripts SQL (`data.sql`) pra inicializar os dados na base

---

## Como rodar localmente

1. **Banco de dados MySQL**

   - Crie um banco chamado `devcinelocadora`
   - Configure seu usuário e senha no `application.properties` (em `src/main/resources`):

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/devcinelocadora?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     spring.jpa.hibernate.ddl-auto=update
     spring.sql.init.mode=always
     spring.sql.init.platform=mysql
     ```

   - O arquivo `data.sql` vai popular a base com filmes, clientes e alugueis automaticamente na inicialização

2. **Build e Run**

   - Compile e rode com Maven:

     ```bash
     mvn clean spring-boot:run
     ```

3. **Swagger UI**

   - Acesse a documentação dos endpoints aqui:

     ```
     http://localhost:8080/swagger-ui.html
     ```

---

## Endpoints principais

### Filmes (`/api/filmes`)

- `POST` cria filme
- `GET /{id}` busca filme por ID
- `GET` lista todos filmes
- `PUT /{id}` atualiza filme
- `DELETE /{id}` deleta filme

### Clientes (`/api/clientes`)

- `POST` cria cliente
- `GET /{id}` busca cliente por ID
- `GET` lista todos clientes
- `PUT /{id}` atualiza cliente
- `DELETE /{id}` deleta cliente

### Aluguéis (`/api/alugueis`)

- `POST` cria aluguel (com lista de filmes e cliente)
- `GET /{id}` busca aluguel por ID
- `GET` lista todos aluguéis
- `DELETE /{id}` deleta aluguel

---

## Tratamento de erros

- Validações retornam status 422 com mensagens detalhadas
- Exceções específicas como `ClienteNotFoundException`, `FilmeNotFoundException`, `AluguelNotFoundException` e `EstoqueInsuficienteException` são capturadas e retornam erros claros para o usuário da API

---

## Curiosidade

Essa API não consome dados externos — tudo gerenciado internamente pra garantir que a massa de dados (filmes, clientes, alugueis) esteja sob controle e permita simular a locadora com estoque variável.

---


