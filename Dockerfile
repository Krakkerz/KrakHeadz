FROM maven:3-openjdk-17 AS build
WORKDIR /tmp
ADD pom.xml .
ADD src src
RUN ["mvn", "-Pstaging", "clean", "install"] only for staging build
#RUN ["mvn", "-Pproduction", "clean", "install"]

FROM openjdk:17 AS run
COPY --from=build /tmp/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]
