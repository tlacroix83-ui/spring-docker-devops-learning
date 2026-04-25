#!/bin/bash

set -e

URL=${1:-http://localhost:8080/health}
MAX_RETRIES=30
SLEEP=2

echo "Waiting for API at $URL..."

for i in $(seq 1 $MAX_RETRIES); do
  if curl -s "$URL" | grep -q "Alive"; then
    echo "API is ready!"
    exit 0
  fi

  echo "Attempt $i/$MAX_RETRIES: API not ready yet..."
  sleep $SLEEP
done

echo "API did not start in time"

# Affiche les logs pour debug
docker compose logs

exit 1