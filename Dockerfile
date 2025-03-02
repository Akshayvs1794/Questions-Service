FROM  openjdk:latest
ADD target/QuestionServiceApp.jar /QuestionServiceApp.jar
EXPOSE 8080
ENTRYPOINT ("java" "-jar" "/QuestionServiceApp.jar")