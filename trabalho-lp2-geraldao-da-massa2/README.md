# Trabalho de LP2 - Geraldao da Massa

Este repositório contém uma aplicação backend em Spring Boot para gerenciamento acadêmico e administrativo. O projeto foi estruturado com controllers, services, repositories, entidades JPA e DTOs para organizar as operações principais.

## O que está implementado

A aplicação já possui módulos para:

- Gestão de usuários, administradores e discentes
- Cadastro e controle de docentes e cursos
- Gerenciamento de oportunidades acadêmicas e inscrições
- Organização de grupos e membros
- Emissão e consulta de certificados e aproveitamentos
- Registro de notificações e alterações de permissões

A estrutura principal está organizada em:

- controllers: endpoints REST da aplicação
- services: regras de negócio
- repositories: acesso a dados via JPA
- entities: modelos de domínio
- DTOs: transferência de dados entre camadas

## Requisitos

- Java 21
- Maven 3.9+

## Como rodar a aplicação

1. Entre na pasta do projeto:
   ```bash
   cd demo
   ```

2. Execute a aplicação com o Maven Wrapper:

   No Windows PowerShell:
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   No Linux/macOS:
   ```bash
   ./mvnw spring-boot:run
   ```

3. A aplicação ficará disponível em:
   - http://localhost:8001

4. O console do banco H2 pode ser acessado em:
   - http://localhost:8001/h2-console

   Credenciais padrão configuradas no arquivo application.properties:
   - Usuário: sa
   - Senha: password

## Como executar os testes

```bash
./mvnw test
```

## Como gerar o pacote da aplicação

```bash
./mvnw clean package
```

## Observações

- A aplicação usa Spring Boot com banco H2 em memória para facilitar execução local.
- A porta padrão configurada é 8001.
