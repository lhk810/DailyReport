FROM java:8
EXPOSE 8080
ARG JAR_FILE=build/libs/report-spring-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} report-spring.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/report-spring.jar"]
