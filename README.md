[![CI](https://github.com/tlacroix83-ui/spring-docker-devops-learning/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/tlacroix83-ui/spring-docker-devops-learning/actions/workflows/ci.yml)

# Todo API — Spring Boot, Docker & PostgreSQL

## 📌 Description

Ce projet est une API REST développée en Java avec Spring Boot, permettant de gérer des tâches (todos).
L’application est conteneurisée avec Docker et orchestrée via Docker Compose, avec une base de données PostgreSQL.

Ce projet s’inscrit dans une démarche de montée en compétence vers des pratiques DevOps (conteneurisation, orchestration, CI/CD).


## 🧰 Stack technique

* Java 17
* Spring Boot
* Spring Data JPA / Hibernate
* PostgreSQL
* Docker / Docker Compose


## Concepts démontrés

* Conception d’une API REST (GET / POST)
* Utilisation de DTO (séparation API / modèle de données)
* Persistance avec PostgreSQL
* Conteneurisation d’une application Java
* Orchestration multi-containers avec Docker Compose
* Gestion des volumes Docker (persistance des données)
* Communication réseau entre containers (DNS Docker)
* Ajout d’une pipeline CI/CD (delivery) (GitHub Actions)


## Architecture

```text
[Client HTTP]
       ↓
[Spring Boot API]
       ↓
[PostgreSQL]
```

* L’API expose des endpoints REST
* Les données sont stockées dans PostgreSQL
* Les services communiquent via le réseau Docker (hostname `postgres`)

---

## ⚙️ Lancer le projet

### Prérequis

* Docker Desktop installé
* Docker Compose disponible

### Commande

```bash
docker compose up --build
```


## Endpoints

### ➕ Créer un todo

```http
POST /todos
```

Body :

```json
{
  "title": "Mon premier todo"
}
```

### 📥 Récupérer les todos

```http
GET /todos
```

---

## Tests

Le projet inclut plusieurs niveaux de tests :

- Tests d’intégration Spring Boot avec MockMvc
- Tests API via scripts (curl) dans un environnement Docker Compose
- Validation du fonctionnement avec PostgreSQL

Objectif : garantir le bon fonctionnement de l’application dans un environnement proche de la production.


---

## 🗄️ Base de données

* PostgreSQL tourne dans un container Docker
* Port exposé : `5432`
* Base : `app`
* User : `user`

Connexion possible via DBeaver.

---

## 💾 Persistance

Les données sont stockées via un volume Docker :

```yaml
volumes:
  postgres_data:
```


## 🔄 Cycle de vie Docker

* `docker compose up --build` : build et lance les services
* `docker compose down` : stoppe les containers
* `docker compose down -v` : supprime aussi les données

---

## 🚀 CI/CD

Mise en place d’une pipeline CI/CD avec GitHub Actions :

- Build Maven automatique
- Tests d’intégration Spring Boot (MockMvc)
- Tests API via Docker Compose (PostgreSQL)
- Build et versioning d’images Docker
- Publication sur GitHub Container Registry (GHCR)
  
Code → GitHub → CI/CD → Tests → Docker image → GHCR → Ready to deploy

---

## 🚧 Évolutions prévues

* Finalisation de la pipeline CI/CD (partie deployment) (GitHub Actions)
* Introduction de microservices
* Déploiement Kubernetes
* Validation des entrées (Bean Validation)
* Gestion des erreurs API

---
