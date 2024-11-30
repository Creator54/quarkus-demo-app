# Getting started with Quarkus and Instrumentation with SigNoz

This is a minimal REST service exposing endpoints over REST with built-in observability using OpenTelemetry and SigNoz.

## Stack
- RESTEasy for REST endpoints
- OpenTelemetry for instrumentation
- SigNoz for observability
- REST-assured and JUnit 5 for testing

## Prerequisites

- JDK 17+
- GraalVM (only for native builds)
- Maven
- Docker (optional, for containerized builds)

## Environment Setup

Set the following environment variables before running the application:

```bash
# Required SigNoz configuration
export OTEL_RESOURCE_ATTRIBUTES=service.name=<your-app-name>
export OTEL_EXPORTER_OTLP_HEADERS="signoz-access-token=<your-signoz-key>"
export OTEL_EXPORTER_OTLP_ENDPOINT=https://ingest.<region>.signoz.cloud:443
```

Replace:
- `<your-app-name>` with your application name
- `<your-signoz-key>` with your SigNoz ingestion key
- `<region>` with your SigNoz cloud region (us, eu, or in)

## Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd <repository-name>
```

### 2. Run the Application

#### Development Mode
```bash
./mvnw quarkus:dev
```

#### Production Mode
```bash
# Build the application
./mvnw package

# Run the application
java -jar target/quarkus-app/quarkus-run.jar
```

### 3. Test the Endpoints

```bash
# Test the hello endpoint
curl http://localhost:8080/hello

# Test the greeting endpoint
curl http://localhost:8080/hello/greeting/YourName
```

## Build Options

### JVM Mode
```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Native Mode
```bash
# Build native executable
./mvnw package -Dnative

# Run the native executable
./target/getting-started-1.0.0-SNAPSHOT-runner
```

### Docker Builds

#### JVM Mode
```bash
# Build using JVM Dockerfile
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-app-jvm .

# Run the container
docker run -i --rm -p 8080:8080 \
  -e OTEL_RESOURCE_ATTRIBUTES \
  -e OTEL_EXPORTER_OTLP_HEADERS \
  -e OTEL_EXPORTER_OTLP_ENDPOINT \
  quarkus-app-jvm
```

#### Native Mode
```bash
# Build using Native Dockerfile
docker build -f src/main/docker/Dockerfile.native -t quarkus-app-native .

# Run the container
docker run -i --rm -p 8080:8080 \
  -e OTEL_RESOURCE_ATTRIBUTES \
  -e OTEL_EXPORTER_OTLP_HEADERS \
  -e OTEL_EXPORTER_OTLP_ENDPOINT \
  quarkus-app-native
```

## Development

### Live Coding
Quarkus offers dev mode with live coding:
```bash
./mvnw quarkus:dev
```

This enables:
- Hot reload for code changes
- Dev UI at http://localhost:8080/q/dev
- Continuous testing

### Testing
```bash
# Run tests
./mvnw test

# Run integration tests
./mvnw verify
```

## Observability with SigNoz

### Verify Instrumentation
1. Start the application
2. Make some test requests
3. Check SigNoz UI:
   - Open Services tab
   - Look for your application name
   - View traces, metrics, and logs

### Troubleshooting
- Ensure environment variables are properly set
- Check application logs for any OpenTelemetry-related errors
- Verify network connectivity to SigNoz endpoints
- For detailed logs, set `quarkus.log.category."io.opentelemetry".level=DEBUG`

## Additional Resources
- [Quarkus Guide](https://quarkus.io/guides/getting-started)
- [OpenTelemetry Documentation](https://opentelemetry.io/docs/)
- [SigNoz Documentation](https://signoz.io/docs/)
