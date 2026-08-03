#!/usr/bin/env sh
set -eu

suite="${1:-smoke}"
shift || true

case "$suite" in
  ui|smoke|api|api-auth|smoke-parallel|ui-parallel)
    ;;
  *)
    echo "Unknown suite: $suite" >&2
    echo "Available suites: ui, smoke, api, api-auth" >&2
    exit 64
    ;;
esac

if [ "$suite" = "api-auth" ] && [ -z "${API_TOKEN:-}" ]; then
  echo "API_TOKEN must be set to run the api-auth suite." >&2
  exit 64
fi

exec mvn --batch-mode --no-transfer-progress \
  clean test \
  "-P${suite}" \
  "-Dtest.run.directory=/workspace/target/${suite}" \
  "$@"
