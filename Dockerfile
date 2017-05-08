FROM frolvlad/alpine-oraclejdk8:slim
ADD pom.xml /app/
ADD src/ /app/src/
WORKDIR /app/
RUN mvn package
EXPOSE  8080
CMD ["java","-jar","target/ihbaby-bbs-0.0.1-SNAPSHOT.jar"]
