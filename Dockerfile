FROM openjdk:11.0.12
COPY . /top-100-three-sequence-words
WORKDIR /top-100-three-sequence-words
RUN ./gradlew build
ENTRYPOINT ["./gradlew", "run"]