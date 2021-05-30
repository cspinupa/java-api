
# Helidon Quickstart SE Example

This project implements a simple Hello World REST service using Helidon SE.

## Prerequisites

1. Maven 3.5 or newer
2. Java SE 8 or newer
3. Docker 17 or newer to build and run docker images
4. Kubernetes minikube v0.24 or newer to deploy to Kubernetes (or access to a K8s 1.7.4 or newer cluster)
5. Kubectl 1.7.4 or newer to deploy to Kubernetes

Verify prerequisites
```
java -version
mvn --version
docker --version
minikube version
kubectl version --short
```

## Build

```
mvn package
```

## Start the application

```
java -jar target/helidon-quickstart-se.jar
```

## Exercise the application

```
curl -X GET http://localhost:8060/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8060/greet/Joe
{"message":"Hello Joe!"}

curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Hola"}' http://localhost:8060/greet/greeting

curl -X GET http://localhost:8060/greet/Jose
{"message":"Hola Jose!"}
```

## Try health and metrics

```
curl -s -X GET http://localhost:8060/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8060/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8060/metrics
{"base":...
. . .

```

## Build the Docker Image

```
docker build -t helidon-quickstart-se .
```

## Start the application with Docker

```
docker run --rm -p 8060:8060 helidon-quickstart-se:latest
```

Exercise the application as described above

## Deploy the application to Kubernetes

```
kubectl cluster-info                # Verify which cluster
kubectl get pods                    # Verify connectivity to cluster
kubectl create -f app.yaml   # Deply application
kubectl get service helidon-quickstart-se  # Get service info
```

## Native image with GraalVM

GraalVM allows you to compile your programs ahead-of-time into a native
 executable. See https://www.graalvm.org/docs/reference-manual/aot-compilation/
 for more information.

You can build a native executable in 2 different ways:
* With a local installation of GraalVM
* Using Docker

### Local build

Download Graal VM at https://github.com/oracle/graal/releases, the version
 currently supported for Helidon is `19.0.0`.

```
# Setup the environment
export GRAALVM_HOME=/path
# build the native executable
mvn package -Pnative-image
```

You can also put the Graal VM `bin` directory in your PATH, or pass
 `-DgraalVMHome=/path` to the Maven command.

See https://github.com/oracle/helidon-build-tools/tree/master/helidon-maven-plugin
 for more information.

Start the application:

```
./target/helidon-quickstart-se
```

### Multi-stage Docker build

Build the "native" Docker Image

```
docker build -t helidon-quickstart-se-native -f Dockerfile.native .
```

Start the application:

```
docker run --rm -p 8060:8060 helidon-quickstart-se-native:latest
```


-------- This is what is shown when minikube started ------------

cpinupa@cpinupa-mac java-api % minikube delete
🔥  Deleting "minikube" in virtualbox ...
💔  The "minikube" cluster has been deleted.
cpinupa@cpinupa-mac java-api % minikube start
😄  minikube v1.4.0 on Darwin 10.15.7
🔥  Creating virtualbox VM (CPUs=2, Memory=2000MB, Disk=20000MB) ...
🐳  Preparing Kubernetes v1.16.0 on Docker 18.09.9 ...
🚜  Pulling images ...
🚀  Launching Kubernetes ... 
⌛  Waiting for: apiserver proxy etcd scheduler controller dns
🏄  Done! kubectl is now configured to use "minikube"

-------------- end --------------

Useful references:
    - https://blogs.oracle.com/dev2dev/get-oracle-jdbc-drivers-and-ucp-from-oracle-maven-repository-without-ides


TODO:
    -  docker build -t employee-app . [Build the Docker image.] (from https://docs.oracle.com/en/solutions/build-rest-java-application-with-oke/deploy-application-oracle-cloud.html#GUID-ED3E352E-F399-40A3-9530-6E436D99D28C)
        - Able to resolve the error but getting into error connecting to DB
            - try changing the ojdbc8 groupId to old value
        - Adding "-Doracle.jdbc.fanEnabled=false" as java options in java command run resolved running the app from docker image
            - using command : "docker run --rm -p 8060:8060 employee-app"