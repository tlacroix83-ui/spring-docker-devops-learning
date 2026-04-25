#!/bin/bash

set -e

echo "Testing POST /todos"
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"CI test todo"}'

echo "Testing GET /todos"
curl -s http://localhost:8080/todos | grep "CI test todo"

echo "Tests OK"