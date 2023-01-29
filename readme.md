
# Mosquitto MQTT - Spring Boot & Android & React JS Location Tracking App

#### The Project consists of 3 parts:

- Backend: Java Spring Boot
- Frontend: React JS
- Mobile: Android Kotlin

#### Technologies

- Spring Boot
- Server Sent Events
- Mosquitto MQQT Server
- Redis


### How to run

#### ! Note that project under the construction. Not at all dockerized! 
#### You have to install Mosquitto MQTT application, I will add it to docker asap.

#### clone the project: https://github.com/gurkanucar/mqtt-example

```bash
  git clone https://github.com/gurkanucar/mqtt-example
```

#### create jar

```bash
  cd backend
  mvn clean install -DskipTests
```

#### build docker-compose

```bash
  docker-compose build --no-cache
```

#### run docker-compose

```bash
  docker-compose up --force-recreate
```

## Example Video

[https://youtu.be/WXTXZTauM7A](https://youtu.be/WXTXZTauM7A)


## Example Images

![image](./images/1.png)

