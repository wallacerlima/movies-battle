FROM adoptopenjdk/openjdk11:alpine
COPY target/service-discovery-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]