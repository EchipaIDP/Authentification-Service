# Authentification-Service

Comenzi docker:
+ mvn clean install pentru creare fisier .jar
+ docker build -t <name> .
+ docker run -p 8080:8080 <name>
+ docker pull mongo
+ docker run -d --name container_name -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=password mongo
