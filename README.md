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
* Validation des entrées (Bean Validation)
* Gestion centralisée des erreurs API (ControllerAdvice)
* Implémentation des codes HTTP REST (201, 400, 404, 500)
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
[Controller (DTO + Validation)]
       ↓
[Service]
       ↓
[Repository]
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
Réponses :
- 201 Created -> Todo créé
- 400 Bad Request -> Données invalides

### 📥 Récupérer les todos

```http
GET /todos
```

---

## Validation & DTO

- Utilisation de DTO pour séparer le modèle API du modèle de données
- Validation des entrées avec Bean Validation (`@Valid`, `@NotBlank`)
- Protection contre les données invalides

---

## ⚠️ Gestion des erreurs

L’API implémente une gestion centralisée des erreurs via `@RestControllerAdvice`.

### Cas gérés :

- `400 Bad Request` → erreurs de validation (DTO)
- `404 Not Found` → ressource inexistante
- `500 Internal Server Error` → erreurs non prévues (fallback global)

### Format de réponse :

```json
{
  "timestamp": "...",
  "status": 400,
  "error": "Bad Request",
  "message": "title must not be blank",
  "path": "/todos"
}
```
---

## Stratégie de tests

Le projet implémente une stratégie de tests multi-niveaux :

### 1. Tests Controller (MockMvc)
- Test de la couche web sans serveur HTTP
- Validation des endpoints REST (codes HTTP, JSON)

### 2. Tests Repository (Testcontainers)
- Utilisation d’une base PostgreSQL réelle
- Tests exécutés dans un container Docker éphémère

### 3. Tests API (Docker Compose)
- Lancement complet de l’application (API + PostgreSQL)
- Tests via scripts curl dans un environnement conteneurisé

Objectif : reproduire un environnement proche de la production et garantir la fiabilité de l’application.


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

Pipeline GitHub Actions :

1. Build Maven
2. Exécution des tests :
   - Tests Spring Boot (MockMvc)
   - Tests d’intégration avec Testcontainers (PostgreSQL)
3. Build de l’application
4. Lancement via Docker Compose
5. Tests API (scripts curl)
6. Build et push de l’image Docker vers GitHub Container Registry (GHCR)

👉 Chaque commit sur `main` déclenche automatiquement la pipeline.
  
Code → GitHub → CI/CD → Tests → Docker image → GHCR → Ready to deploy

---

## 🚧 Évolutions prévues

* Finalisation de la pipeline CI/CD (partie deployment) (GitHub Actions)
* Introduction de microservices
* Déploiement Kubernetes
* Gestion centralisée des erreurs (ExceptionHandler)
* Gestion des erreurs API

---
