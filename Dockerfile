# Stage 1: Build
FROM eclipse-temurin:25-jdk-alpine AS build
# Install Maven manually since an official 'maven:*-java25' image doesn't exist yet
RUN apk add --no-cache maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]