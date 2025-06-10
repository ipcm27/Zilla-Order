# Etapa 1 - Builder com Maven 3.9.9 + Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app/product-service

# Copia o pom.xml do pai (necessário para resolver o parent)
COPY pom.xml /app/pom.xml

# Copia apenas o pom.xml do módulo para aproveitar cache de dependências
COPY product-service/pom.xml ./pom.xml

# Baixa dependências do Maven com base no pom.xml (melhor para cache em builds futuros)
RUN mvn dependency:go-offline

# Agora copia todo o restante do módulo
COPY product-service/. .

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Etapa 2 - Runtime apenas com Java 21 (mais leve)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o JAR gerado na etapa de build para o container final
COPY --from=build /app/product-service/target/product-service-*.jar app.jar

# Expõe a porta padrão do serviço (ajuste conforme necessário)
EXPOSE 8086

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]