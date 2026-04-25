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

## Tester l’API

### PowerShell

```powershell
Invoke-RestMethod `
  -Uri "http://localhost:8080/todos" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"title":"Test"}'
```

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/todos"
```

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

## 🚧 Évolutions prévues

* Ajout d’une pipeline CI/CD (GitHub Actions)
* Introduction de microservices
* Déploiement Kubernetes
* Validation des entrées (Bean Validation)
* Gestion des erreurs API

---
