FROM gradle:4.9-jdk8

USER root

ARG DEV_UID=1000
ARG DEV_GID=1000

# Création de l'utilisateur dev matchant l'utilisateur local (le plus souvent 1000:1000)
RUN groupadd -g $DEV_GID dev \
  && useradd -u $DEV_UID -g dev dev
RUN mkdir /home/dev && chown dev:dev /home/dev

EXPOSE 8080

USER dev