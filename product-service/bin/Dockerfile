# Imagen base
FROM eclipse-temurin:21-jdk

# Nombre del jar
ARG JAR_FILE=target/product-service-0.0.1-SNAPSHOT.jar

# Copiar jar al contenedor
COPY ${JAR_FILE} app.jar

# Ejecutar
ENTRYPOINT ["java","-jar","/app.jar"]