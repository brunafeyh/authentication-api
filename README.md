
---

# Authentication API

**Authentication API** é um serviço RESTful desenvolvido com Spring Boot para gerenciar a autenticação de usuários utilizando JSON Web Tokens (JWT). Ele oferece funcionalidades para criação de usuários com diferentes papéis, login de usuários e validação de JWTs.

## Índice

- [Funcionalidades](#funcionalidades)
- [Uso](#uso)

## Funcionalidades

- **Criação de Usuário**: Permite registrar usuários com um dos três papéis: Cliente (Customer), Atendente (Attendant) ou Administrador (Administrator).
- **Login de Usuário**: Autentica usuários e gera JWTs para gerenciamento seguro de sessões.
- **Validação de JWT**: Valida JWTs para proteger os endpoints da API, garantindo que apenas usuários autenticados acessem os recursos.

## Uso

### Swagger UI

Você pode explorar os endpoints da API utilizando a interface do Swagger UI:

[Swagger UI](https://menu-master-production.up.railway.app/swagger-ui.html)

---
