# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto omitiendo las pruebas para mayor velocidad
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copia el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/entrenadores-0.0.1-SNAPSHOT.jar app.jar
# Expone el puerto 8081 que configuramos previamente
EXPOSE 8081
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="lisan"
