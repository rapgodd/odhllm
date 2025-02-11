#PROD
FROM eclipse-temurin:17-jdk-alpine
COPY ./build/libs/*SNAPSHOT.jar project.jar
ENTRYPOINT ["java", "-jar", "project.jar"]


#개발
#FROM eclipse-temurin:17-jdk
#COPY ./build/libs/*SNAPSHOT.jar project.jar
#ENTRYPOINT ["java", "-jar", "project.jar"]
