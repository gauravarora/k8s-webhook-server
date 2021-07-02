FROM guneetgi
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8080
ADD ${JAR_FILE} target/k8s-validator-0.0.1-SNAPSHOT.jar
CMD "java -Djava.security.egd=file:/dev/./urandom -jar /k8s-validator-0.0.1-SNAPSHOT.jar"
