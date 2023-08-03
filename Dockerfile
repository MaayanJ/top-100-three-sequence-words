FROM openjdk:11.0.12
COPY src/ /src/
RUN javac /src/main/java/Main.java -d /app
WORKDIR /app

CMD ["java", "Main"]
ENTRYPOINT ["java", "Main"]