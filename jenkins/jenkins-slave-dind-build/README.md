# Jenkins slave build with dind

This recipe lets you build jenkins slave with docker in docker(dind).

## Build jenkins-slave-dind image

```bash
docker build -t jenkins-slave-dind:latest .
```

## Use docker compose build jenkins slaves
```bash
docker-compose --privileged -f slave-compose.yml up --build
```it 