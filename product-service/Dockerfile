# =========================
# 🔨 STAGE 1: BUILD
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia todo el proyecto
COPY . .

# Compila el proyecto (sin tests para mayor velocidad)
RUN mvn clean package -DskipTests


# =========================
# 🚀 STAGE 2: RUN
# =========================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia solo el JAR desde el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java","-jar","app.jar"]