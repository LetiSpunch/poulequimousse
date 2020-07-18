FROM java:8

ARG VERSION=1.0.1

COPY build/libs/lapoulequimousse-$VERSION.jar ./lapoulequimousse.jar

COPY .docker/services/lapoulequimousse/entrypoint.sh /
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

EXPOSE 8080