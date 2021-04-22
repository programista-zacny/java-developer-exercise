FROM openjdk:11.0.11-jdk
MAINTAINER jacek.lacny
RUN adduser --system --group spring
USER spring
COPY build/libs/java-developer-exercise-0.0.1-SNAPSHOT.jar /home/spring/app.jar
WORKDIR /home/spring
ENTRYPOINT ["java", "-jar", "app.jar"]
