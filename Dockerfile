FROM maven:3.5.4-jdk-8-alpine as builder
RUN mkdir /crudplayground/
COPY ./ /crudplayground/
WORKDIR /crudplayground/
RUN mvn clean install assembly:single

FROM openjdk:8-jdk-alpine
COPY --from=builder /crudplayground/target/crudplayground-1.0-SNAPSHOT-jar-with-dependencies.jar ./crudplayground/
COPY --from=builder /crudplayground/config.yml ./crudplayground/
WORKDIR /crudplayground/

CMD java -jar crudplayground-1.0-SNAPSHOT-jar-with-dependencies.jar db migrate config.yml && java -jar crudplayground-1.0-SNAPSHOT-jar-with-dependencies.jar server config.yml
EXPOSE 8080