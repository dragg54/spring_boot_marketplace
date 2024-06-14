FROM openjdk:17-oracle

WORKDIR /app

COPY target/ecommerce-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "application.jar"]
