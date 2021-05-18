FROM maven:3.8.1-openjdk-11
COPY target/LocalStoreFinder-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/LocalStoreFinder-0.0.1-SNAPSHOT.jar"]