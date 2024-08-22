# Coding Exercise

## Requirements

The projects have been designed for Java 17, Gradle Wrapper has been included for builds.

## Layout

| Project Folder      | Description                         |
|---------------------|-------------------------------------|
| customer-file-load  | Loader for Customer CSV File        |
| customer-file-model | Common JSON Definition for Customer |
| customer-file-svcs  | Web Services for Customer Data      |
| string-calculator   | Kata Coding Exercise                |

## Customer File Loader

This project is found under customer-file-load

### Configuration

It has the following properties in the application.yml file

|Property|Description|Default Value|
|--------|-----------|-------------|
|customer.file.service.url|URL for the Customer Service to save the CSV entries|http://localhost:9090/customer/save|

### Operation

This can be run from the command line with the following command:

~~~
java -jar build/libs/customer-file-load.jar <Name of CSV File>
~~~

This is assuming this is done from the customer-file-load directory. This will invoke the Spring Boot application and load the CSV and send the JSONs to the service.

## Customer File Services

This project is found under customer-file-svcs

### Configuration

It has the following properties in the application.yml file

|Property|Description|Default Value|
|--------|-----------|-------------|
|server.port|Port Number for the Web Services|9090|

### Operation

This runs using an internal memory H2 database to make the application easier to run but can run under any database system providing the correct users, tables and grants have been created.

This can be run from the command line with the following command:

~~~
java -jar build/libs/customer-file-svcs.jar
~~~

This is assuming this is done from the customer-file-svcs directory. This will invoked the Spring Boot application and will serve the services the definition of which is shown below:

|Service URL|Method Type|Request|Response|
|-----------|-----------|-------|--------|
|/customer/save|POST|Customer Data JSON|HTTP Response Code 200 or 500|
|/customer/{customerRef}|GET|customerRef Path Variable|Customer Data JSON or 500 HTTP Response|

## String Calculator

This does not have any specific executable part other than unit tests. These can be run by importing the project of using the gradle test task.
