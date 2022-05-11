FROM arm64v8/alpine

RUN apk update
RUN apk add openjdk17 maven git python3

RUN mkdir /context
RUN touch /context/source /context/input /context/output

RUN git clone https://github.com/Kenyo-cell/transfer-objects.git
RUN mvn -f transfer-objects/pom.xml clean install
RUN rm -r transfer-objects

WORKDIR /project

COPY ./src /project/src/
COPY ./pom.xml /project/

RUN mvn clean package spring-boot:repackage

RUN rm -r src

EXPOSE 9000

CMD [ "java", "-jar", "target/ContainerExecutor-1.0-SNAPSHOT.jar" ]
