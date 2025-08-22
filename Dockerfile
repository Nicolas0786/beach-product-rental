# ====== Etapa de build ======
FROM maven:3.9.8-eclipse-temurin-11 AS build
WORKDIR /app
  # Copia apenas o pom.xml primeiro para aproveitar cache
COPY pom.xml .
RUN mvn clean verify --fail-never
  # Copia o código e compila
COPY src ./src
RUN mvn clean package -DskipTests

  # ====== Etapa de runtime ======
FROM eclipse-temurin:11-jre
WORKDIR /app
  # Ajuste o nome do JAR conforme seu projeto (ex: minha-app-0.0.1-SNAPSHOT.jar)
COPY --from=build /app/target/*.jar beach-product-rental-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENV TZ=America/Sao_Paulo
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/beach-product-rental-0.0.1-SNAPSHOT.jar"]
