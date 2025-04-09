#Fase de Build
FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /api-rpg
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean install

#Fase de runtime
FROM amazoncorretto:17-alpine3.17
LABEL MAINTAINER="Guilherme Cassiano"
ENV PORT=8085
WORKDIR usr/src/app
RUN rm -f /etc/localtime && ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
COPY --from=build /api-rpg/target/*.jar /usr/src/app/rpg.jar

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/usr/src/app/rpg.jar", "--server.port=${PORT}"]

EXPOSE ${PORT}