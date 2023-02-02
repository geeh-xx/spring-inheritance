FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/*.jar /app
EXPOSE 5000
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app.jar"]