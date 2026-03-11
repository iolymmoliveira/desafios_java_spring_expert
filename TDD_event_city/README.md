# Event City — Desafio TDD

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge)

> Desafio prático de TDD desenvolvido durante a Formação Spring Boot Expert. O objetivo é implementar as funcionalidades de um sistema de eventos e cidades a partir de testes automatizados já fornecidos — o código de produção deve ser escrito para fazer os testes passarem.

---

## 📋 Sumário

- [Sobre o Desafio](#-sobre-o-desafio)
- [Modelo de Domínio](#-modelo-de-domínio)
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Como executar](#-como-executar)
- [Autor](#-autora)

---

## 📌 Sobre o Desafio

<!-- TODO: descrever o que foi implementado ao longo do desafio -->

---

## 🗂 Modelo de Domínio

O sistema possui dois recursos principais com uma relação **N-1** entre eles:

- Um **Event** pertence a uma **City**
- Uma **City** pode ter vários **Events**

| Entidade | Atributos |
|---|---|
| Event | id, name, date, url, city |
| City | id, name |

---

## 🛠 Tecnologias utilizadas

| Categoria | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.4.3 |
| Persistência | Spring Data JPA / Hibernate |
| Banco de Dados | H2 (testes) |
| Testes | JUnit 5, Mockito, MockMvc |
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

# Execute com Maven
./mvnw spring-boot:run
```

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
