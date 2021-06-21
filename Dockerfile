FROM adoptopenjdk/openjdk8
WORKDIR /
ARG LoginServiceDao-0.0.1-SNAPSHOT.jar
ADD LoginServiceDao-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8103
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]