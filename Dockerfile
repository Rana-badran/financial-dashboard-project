# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /target/financial-dashboard-0.0.1-SNAPSHOT.jar financial-dashboard.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","financial-dashboard.jar"]

# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/financial-dashboard-0.0.1-SNAPSHOT.jar financial-dashboard.jar

# Use the PORT environment variable provided by Render or default to 8080
EXPOSE 8080
ENV PORT=8080
CMD ["java", "-jar", "financial-dashboard.jar"]
