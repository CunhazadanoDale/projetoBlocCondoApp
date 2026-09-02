 # 🏢 Bloc — API de Gestão de Condomínios

API REST desenvolvida em **Java + Spring Boot** para digitalizar a gestão de condomínios: moradores, prestadores de serviço, agendamento de espaços comuns, chamados e movimentação financeira, com autenticação e autorização por papéis.

> Projeto pessoal em desenvolvimento ativo, aplicando práticas que uso no dia a dia como backend Júnior: migrations versionadas, testes de integração, cache/lock distribuído e containerização.

---

## Funcionalidades

- **Autenticação e contas** — login e registro com JWT, controle de acesso por papel (`MORADOR`, `PRESTADOR`, `SINDICO`, `ADMIN`).
- **Moradores** — cadastro e consulta de perfil vinculado a uma conta e a um condomínio.
- **Prestadores de serviço** — cadastro de prestadores, com dados públicos e privados separados, e serviços oferecidos.
- **Espaços comuns e agendamentos** — cadastro de espaços do condomínio, consulta de disponibilidade por data e reserva/cancelamento de horários.
- **Modelagem pronta para expansão** — chamados/tickets de manutenção, comunicados por condomínio e movimentações financeiras (saques) já estão modelados no banco (migrations) e via entidades, como próxima camada de API a ser exposta.

---

## Arquitetura

O projeto é organizado por **domínio de negócio** (e não por camada técnica), o que facilita localizar e evoluir cada funcionalidade isoladamente:

```
com.condoapp.bloc
├── auth          → login, registro, JWT, filtro de segurança
├── morador       → perfil e dados do morador
├── prestador     → perfil, serviços e contratações do prestador
├── condominio    → entidade base do condomínio
├── agendamento   → espaços comuns e reservas
├── chamados      → tickets de manutenção/ocorrências (modelado)
├── comunicado    → avisos por condomínio (modelado)
├── pagamentos    → saldo e saques (modelado)
└── util          → objetos de apoio (ex.: Endereço, como @Embeddable)
```

Cada domínio segue o padrão **Controller → Service → Repository**, com DTOs de entrada/saída e mapeamento dedicado (`*Mapper`), evitando expor entidades JPA diretamente na API.

---

## Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/auth/login` | Autentica e retorna o token JWT |
| `POST` | `/auth/registrar` | Cria uma nova conta |
| `GET` | `/moradores/{uuid}` | Busca um morador pelo UUID |
| `POST` | `/moradores` | Completa o perfil de morador da conta autenticada |
| `GET` | `/prestadores/{uuid}` | Busca dados públicos de um prestador |
| `POST` | `/prestadores` | Completa o perfil de prestador da conta autenticada |
| `GET` | `/condominios/{uuid}/espacos` | Lista os espaços comuns de um condomínio |
| `POST` | `/condominios/espacos` | Cadastra um novo espaço comum |
| `PUT` | `/condominios/espacos/{espacoId}` | Atualiza um espaço comum |
| `GET` | `/condominios/espacos/{espacoUUID}/disponibilidade?data=` | Consulta horários disponíveis em uma data |
| `GET` | `/condominios/{uuid}/agendamentos` | Lista os agendamentos de um condomínio |
| `POST` | `/condominios/{uuid}/espacos/{espacoId}/agendamentos` | Cria uma reserva |
| `DELETE` | `/condominios/agendamentos/{agendamentoUuid}` | Cancela uma reserva |

---

## Stack técnica

- **Java 21+ / Spring Boot** (`Web`, `Security`, `Validation`, `Data JPA`)
- **PostgreSQL** como banco relacional, com **Flyway** versionando o schema (9 migrations, cobrindo desde contas/condomínio até índices e locks)
- **Redis** para cache/suporte a operações que exigem baixa latência
- **ShedLock** (`shedlock-spring` + `shedlock-provider-jdbc-template`) para evitar execução concorrente de jobs agendados em múltiplas instâncias
- **JWT** (`jjwt`) para autenticação stateless
- **Lombok** para reduzir boilerplate
- **Docker Compose** para subir o ambiente local (PostgreSQL, Redis e Adminer para inspeção do banco)
- **JUnit 5 + Testcontainers** para testes de integração com banco real em container

---

## Como rodar localmente

### Pré-requisitos
- JDK 21+
- Docker e Docker Compose

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/CunhazadanoDale/projetoBlocCondoApp.git
cd projetoBlocCondoApp

# 2. Suba a infraestrutura (Postgres, Redis e Adminer)
docker compose up -d

# 3. Rode a aplicação (o Spring Boot Docker Compose plugin
# também consegue subir os containers automaticamente ao iniciar)
./mvnw spring-boot:run
```

A aplicação sobe por padrão em `http://localhost:8080`. O Adminer fica disponível em `http://localhost:6444` para consultar o banco (`blocapp` / `blocapp`).

### Rodando os testes

```bash
./mvnw test
```

Os testes de integração usam Testcontainers, então é necessário ter o Docker em execução.

---

## Próximos passos

- Expor endpoints REST para **chamados**, **comunicados** e **pagamentos** (hoje modelados no banco, mas ainda sem camada de API)
- Documentação da API com OpenAPI/Swagger
- Paginação e filtros nas listagens
- Deploy de uma versão de demonstração

---

## Autor

**Gabriel Cunha**
[LinkedIn](https://www.linkedin.com/in/gabrielcunha12324/) · [GitHub](https://github.com/CunhazadanoDale)
