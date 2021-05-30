# java-api
This repo hosts various REST based API's built using java 

1. Helidon SE App: employee-app

TODO:
    - Not able to build the image (need to debug)
        - Check with naming the project as employee-app vs naming the api as employee-api
        - After above, next steps: https://docs.oracle.com/en/solutions/build-rest-java-application-with-oke/prepare-application-cloud-deployment.html#GUID-7DC85446-4FA4-4A64-8A94-C6252F937127
    - Update client artifacts (index and js file)
    - Add tests
    - Add javaDocs  
    - Expose openApi
    - Extend other end-points
    - Connect to other DB's (h2-for in memory, mongo, mysql, oracle)
    - Evaluate logs from docker 
    - Check metrics via zipkin - promethues 
    - Need to setup github actions
    - Deploy to cloud instance

Deploy a microservices-based RESTful Java application to Oracle Cloud
    https://docs.oracle.com/en/solutions/build-rest-java-application-with-oke/index.html#GUID-FBF649D6-0313-48C6-BCB9-9A388937FB1D
    https://docs.oracle.com/en/solutions/develop-microservice-java-app/index.html#GUID-7B47D70F-9DD2-46FC-8E3D-5C6F8C775E54
Code reference:
    https://github.com/oracle/helidon/blob/1.1.2/examples/employee-app/src/main/java/io/helidon/service/employee/EmployeeService.java

Other references:
    Useful read on jsonb:
        https://www.baeldung.com/java-json-binding-api
        https://developer.ibm.com/languages/java/articles/j-javaee8-json-binding-1/

2. Spring boot App: <to be created>