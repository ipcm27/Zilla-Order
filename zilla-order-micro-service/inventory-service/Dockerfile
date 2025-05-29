# Etapa 1 - Builder com Maven 3.9.9 + Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia apenas os arquivos de build primeiro para cache de dependência
COPY pom.xml ./
COPY inventory-service/pom.xml inventory-service/pom.xml

# Baixa dependências (usar cache aqui é ótimo)
RUN mvn dependency:go-offline

# Agora copia o restante do projeto
COPY . .

# Compila apenas o módulo necessário
RUN mvn clean package -pl inventory-service -am -DskipTests

# Etapa 2 - Runtime com apenas Java 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o JAR gerado do módulo
COPY --from=build /app/inventory-service/target/inventory-service-*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "app.jar"]
