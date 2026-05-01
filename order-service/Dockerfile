# =========================
# 🔨 STAGE 1: BUILD
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia todo el proyecto
COPY . .

# Compila el proyecto
RUN mvn clean package -DskipTests


# =========================
# 🚀 STAGE 2: RUN
# =========================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia el jar generado
COPY --from=build /app/target/*.jar app.jar

# Puerto estándar de Spring Boot
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java","-jar","app.jar"]