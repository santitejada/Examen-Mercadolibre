# ================================
# ETAPA 1: BUILD (compilación)
# ================================
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos pom y código fuente
COPY pom.xml .
COPY src ./src

# Compilamos y generamos el JAR (sin tests para que sea más rápido)
RUN mvn -q -DskipTests package

# ================================
# ETAPA 2: RUNTIME (ejecución)
# ================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
# Usamos wildcard *.jar para no preocuparnos por el nombre exacto
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
