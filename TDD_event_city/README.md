# Event City — Desafio TDD

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/status-concluído-brightgreen?style=for-the-badge)

> Desafio prático de TDD desenvolvido durante a Formação Spring Boot Expert. O código de produção foi escrito **a partir dos testes** — nenhuma funcionalidade foi implementada sem um teste guiando a decisão.

---

## 📋 Sumário

- [Sobre o Desafio](#-sobre-o-desafio)
- [A Abordagem TDD](#-a-abordagem-tdd)
- [O que foi implementado](#-o-que-foi-implementado)
- [Modelo de Domínio](#-modelo-de-domínio)
- [Endpoints da API](#-endpoints-da-api)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Como executar](#-como-executar)
- [Autora](#-autora)

---

## 📌 Sobre o Desafio

O projeto **Event City** é um sistema de gerenciamento de eventos e cidades com uma relação N-1 entre eles. O diferencial deste desafio é que os testes de integração foram fornecidos prontos — e a missão foi implementar todo o código de produção para fazê-los passar, sem nenhuma funcionalidade a mais do que o necessário.

Esse formato replica uma situação real de mercado onde a especificação do comportamento esperado já existe (seja em forma de testes, contratos ou critérios de aceite) e o desenvolvedor precisa entregar exatamente o que foi especificado.

---

## 🧪 A Abordagem TDD

Neste desafio, os testes ditaram cada decisão de implementação. O fluxo seguido foi:

**1. Ler o teste → entender o comportamento esperado**

Antes de escrever qualquer linha de código de produção, analisei o que cada teste estava verificando: qual endpoint, qual status HTTP, quais campos no corpo da resposta e em qual cenário (ID existente, ID inexistente, ID com dependência).

**2. Implementar o mínimo para o teste passar**

Nenhuma funcionalidade foi adicionada por antecipação. Se o teste não pedia, não foi implementado.

**3. Garantir que todos os testes passassem juntos**

Após cada implementação, todos os testes foram rodados para garantir que nenhum cenário já aprovado fosse quebrado.

### Testes implementados

**`CityControllerIT`**

| Teste | Comportamento verificado |
|---|---|
| `findAllShouldReturnAllResourcesSortedByName` | GET `/cities` retorna lista ordenada por nome |
| `insertShouldInsertResource` | POST `/cities` persiste e retorna 201 Created |
| `deleteShouldReturnNoContentWhenIndependentId` | DELETE `/cities/{id}` retorna 204 quando ID não tem dependentes |
| `deleteShouldReturnNotFoundWhenNonExistingId` | DELETE `/cities/{id}` retorna 404 quando ID não existe |
| `deleteShouldReturnBadRequestWhenDependentId` | DELETE `/cities/{id}` retorna 400 quando ID tem eventos associados |

**`EventControllerIT`**

| Teste | Comportamento verificado |
|---|---|
| `updateShouldUpdateResourceWhenIdExists` | PUT `/events/{id}` atualiza e retorna 200 com dados corretos |
| `updateShouldReturnNotFoundWhenIdDoesNotExist` | PUT `/events/{id}` retorna 404 quando ID não existe |

---

## ✅ O que foi implementado

A partir dos testes, toda a stack em camadas foi construída do zero:

- **Repositories** — `CityRepository` com query derivada ordenada por nome; `EventRepository`
- **Services** — `CityService` com `findAll`, `insert` e `delete` com tratamento de integridade referencial; `EventService` com `update`
- **Controllers** — `CityController` e `EventController` com mapeamento REST completo
- **Exception Handling** — `ResourceNotFoundException`, `DatabaseException`, `StandardError` e `ControllerExceptionHandler` com respostas padronizadas para `404 Not Found` e `400 Bad Request`

---

## 🗂 Modelo de Domínio

```
┌─────────────────────┐          ┌──────────────┐
│        Event        │          │     City     │
│─────────────────────│   N — 1  │──────────────│
│ id : Long           │─────────▶│ id : Long    │
│ name : String       │          │ name : String│
│ date : LocalDate    │          └──────────────┘
│ url : String        │
│ city : City         │
└─────────────────────┘
```

---

## 🔗 Endpoints da API

### Cities

```
GET    /cities          → Lista todas as cidades ordenadas por nome
POST   /cities          → Cadastra uma nova cidade
DELETE /cities/{id}     → Remove uma cidade (400 se tiver eventos vinculados)
```

### Events

```
PUT    /events/{id}     → Atualiza um evento existente
```

---

## 📁 Estrutura do Projeto

```
src/
├── main/java/com/devsuperior/bds02/
│   ├── controllers/
│   │   ├── handlers/
│   │   │   └── ControllerExceptionHandler.java
│   │   ├── CityController.java
│   │   └── EventController.java
│   ├── dto/
│   │   ├── CityDTO.java
│   │   └── EventDTO.java
│   ├── entities/
│   │   ├── City.java
│   │   └── Event.java
│   ├── repositories/
│   │   ├── CityRepository.java
│   │   └── EventRepository.java
│   └── services/
│       ├── exceptions/
│       │   ├── DatabaseException.java
│       │   └── ResourceNotFoundException.java
│       ├── CityService.java
│       └── EventService.java
└── test/java/com/devsuperior/bds02/
    └── controllers/
        ├── CityControllerIT.java
        └── EventControllerIT.java
```

---

## 🛠 Tecnologias utilizadas

| Categoria | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.4.3 |
| Persistência | Spring Data JPA / Hibernate |
| Banco de Dados | H2 (em memória) |
| Testes de Integração | JUnit 5, MockMvc |
| Build | Maven 4.0.0 |

---

## ✅ Pré-requisitos

- [Java 21](https://adoptium.net/)
- [Maven 4.0.0](https://maven.apache.org/)
- [Git](https://git-scm.com/)

---

## ▶️ Como executar
```bash
# Clone o repositório
git clone git@github.com:iolymmoliveira/desafios_java_spring_expert.git
cd TDD_event_city

# Execute a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### Rodando os testes

```bash
./mvnw test
```

---

## 👩🏻‍💻 Autora:

Feito por **Ioly Oliveira** durante a Formação Spring Boot Expert.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/iolymmoliveira)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/iolymmoliveira)
---
