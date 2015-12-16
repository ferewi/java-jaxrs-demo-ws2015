# java-jaxrs-demo-ws2015
Demo-application for my talk in the seminary "Internetprogrammierung" at the Friedrich-Schiller-Universität Jena.
The application demonstrates the implementation of an RESTful Webservice using JAX-RS/Jersey.

## Clone Repository
```bash
$ git clone https://github.com/jimtonic/java-jaxrs-demo-ws2015.git
```

## Compile the application
```bash
$ mkdir out/app
$ cd out/app
$ javac -d . -cp .:../../lib/* ../../src/*.java ../../src/*/*/*.java
```

## Start the application
In the directory out/app
```bash
$ java -cp .:../../lib/* Server
```