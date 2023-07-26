# Automated Tests for the Onliner.by website catalog

## General info
* Test 1. Check the search results.
* Test 2. Check the filtering results.
* Test 3. Find the product at the lowest price. Сheck that it is in the basket.
* Test 4. Check the filling of the order form fields.

## Technologies
Project is created with:
* Java 11
* Apache Maven 3.8.6 
* TestNG 7.8.0
* Selenide 6.15.0
* Allure 2.22.1       
* Cucumber 7.13.0
  
## Launch
to run the default test go to project dir and run:
```
mvn clean test
```

to run test 1 in a local Chrome browser:
```
mvn clean test -Dsuite=src/test/resources/local_test_1.xml
```

to run test 1 in a Docker Selenoid browser:
```
mvn clean test -Dsuite=src/test/resources/docker_test_1.xml
```
(before that, you need to install https://aerokube.com/cm/latest/ and start the Selenoid on http://localhost:4444/)


