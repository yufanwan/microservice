# Jenkins slave build with dood

This recipe lets you build jenkins slave with docker outside of docker(dood).

Tips: not support on windows.

## Build jenkins-slave-dood image

```bash
docker build -t jenkins-slave-dood:latest .
```

## Use docker compose build jenkins slaves
```bash
DOCKER_PATH=$(which docker) docker-compose -f slave-compose.yml up --build
```