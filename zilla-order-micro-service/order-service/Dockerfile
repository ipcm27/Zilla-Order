# Etapa 1 - Build com Maven + Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia os POMs primeiro para usar cache de dependências
COPY pom.xml ./
COPY order-service/pom.xml order-service/pom.xml

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o restante do projeto
COPY . .

# Compila só o módulo do order-service
RUN mvn clean package -pl order-service -am -DskipTests

# Etapa 2 - Runtime com Java 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/order-service/target/order-service-*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

