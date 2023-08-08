#FROM openjdk:11.0.12
#COPY src/ /src/
#RUN javac /src/main/java/Main.java -d /app
#WORKDIR /app
#
#CMD ["java", "Main"]
#ENTRYPOINT ["java", "Main", "--args='src/main/resources/mobydick.txt'"]

FROM openjdk:12
COPY . /top-100-three-sequence-words
WORKDIR /top-100-three-sequence-words
RUN ./gradlew build
ENTRYPOINT ["./gradlew", "run"]
#CMD ["--args='src/main/resources/mobydick.txt'"]