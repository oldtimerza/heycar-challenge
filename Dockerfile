FROM openjdk:8

ARG JAR_FILE
ADD target/${JAR_FILE} /heycar-challenge.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar","/heycar-challenge.jar"]
