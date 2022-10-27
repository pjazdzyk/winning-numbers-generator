FROM openjdk:17-jdk-slim-buster
EXPOSE 8001
COPY /target/winning-numbers-generator-*.jar /winning-numbers.jar
ENTRYPOINT ["java","-jar","/winning-numbers.jar"]