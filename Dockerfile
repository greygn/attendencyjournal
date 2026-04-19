# ---------- Stage 1: build ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

# ---------- Stage 2: runtime ----------
FROM eclipse-temurin:21-jre

RUN useradd -m -u 1001 appuser

WORKDIR /app

COPY --from=build --chown=appuser:appuser /app/target/*.jar app.jar

USER appuser

HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD java -cp /app/app.jar org.springframework.boot.loader.PropertiesLauncher || exit 1

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]