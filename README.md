WAES Assignment
===================
This is the assignment for Tomás Kroth
# Dependencies
In order to run you'll need:

 - Java 8+
 - Gradle 3.3+

# Building & Running

On the root of the project, run:

> gradle clean build

All tests should be run at this moment and the project should build. Then run:
> java -jar build/libs/WAES_Assignment.jar
# Using
The application will be running on localhost:8080 and the following API's will be available:

>**GET**:   http://localhost:8080/v1/diff/{ID}

>**POST**:  http://localhost:8080/v1/diff/{ID}/right

>**POST**:  http://localhost:8080/v1/diff/{ID}/left

# Architecture Used
I've used Alistair Cockburn's Hexagonal Architecture and DDD for this implementation.

The central part of the application is the Domain objects, and they should not have any dependencies

The Service layer holds actual logic based on the Domain objects, exposing interfaces for what it needs implemented by the infrastructure layer.

The Infrastructure layer is responsible for all external connections, be it Input or Output. So all technology specific implementations are here, like REST endpoint, Database connector, Server configurations and so on.