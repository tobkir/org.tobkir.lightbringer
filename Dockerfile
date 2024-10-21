FROM eclipse-temurin:21-jre
RUN mkdir /app && chown -R 185:185 /app


COPY --chown=185 /target/lightbringer-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

USER root

RUN locale-gen --no-purge de_DE.UTF-8

USER 185

ENV LANG=de_DE.UTF-8
ENV LANGUAGE=de_DE.UTF-8
ENV LC_ALL=de_DE.UTF-8
ENV TZ=Europe/Berlin

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "/app/app.jar"]
