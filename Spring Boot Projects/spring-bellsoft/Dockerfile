FROM bellsoft/liberica-runtime-container:jdk-all-slim-glibc
VOLUME /tmp
ARG JAR_FILE=build/libs/spring-bellsoft-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]