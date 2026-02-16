FROM maven:3.5.2-jdk-8 AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package -DskipTests

FROM tomcat:9.0-jre8-alpine
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/eschool.jar eschool.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "eschool.jar"]