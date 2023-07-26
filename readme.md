# Automated Tests for the Onliner.by website catalog

## Test 1. Check the search results.
## Test 2. Check the filtering results.
## Test 3. Find the product at the lowest price. Сheck that it is in the basket.
## Test 4. Check the filling of the order form fields.

## Technologies:
Java 11
Apache Maven 3.8.6 
TestNG 7.8.0
Selenide 6.15.0
Allure 2.22.1       
Cucumber 7.13.0
        
## Launch:
to run default test:
in project dir run 
```java
mvn clean test
```

to run tests in a local Chrome browser:
```bash
mvn clean test
```
mvn clean test -Dsuite=src/test/resources/local_test_1.xml (run test 1)

...
mvn clean test -Dsuite=src/test/resources/local_test_4.xml (run test 4)
mvn clean test -Dsuite=src/test/resources/local_test_all.xml  (run tests 1,2,3,4)

to run tests in a Docker Selenoid browser
mvn clean test -Dsuite=src/test/resources/docker_test_1.xml (run test 1)
...
mvn clean test -Dsuite=src/test/resources/docker_test_4.xml (run test 4)
mvn clean test -Dsuite=src/test/resources/docker_test_all.xml  (run tests 1,2,3,4)


