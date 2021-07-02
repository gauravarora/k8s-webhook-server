FROM guneetginnigarg/openjdk:11-alpine
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8080
ADD ${JAR_FILE} k8s-validator-0.0.1-SNAPSHOT.jar
COPY target/k8s-validator-0.0.1-SNAPSHOT.jar k8s-validator-0.0.1-SNAPSHOT.jar
COPY docker/entrypoint.sh /entrypoint.sh
RUN chmod 555 /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]