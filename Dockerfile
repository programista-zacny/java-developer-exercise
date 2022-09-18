FROM openjdk:17.0.2
MAINTAINER jacek.lacny

ARG jarFileName

RUN useradd --system --user-group app
USER app
COPY $jarFileName /home/app/app.jar
WORKDIR /home/app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
