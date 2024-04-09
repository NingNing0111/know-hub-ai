FROM arm64v8/openjdk:17
ENV DEFAULT_BASE_URL=""
ENV DEFAULT_API_KEY=""
ARG JAR_FILE=target/*.jar
COPY ./target/know-hub-ai-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

