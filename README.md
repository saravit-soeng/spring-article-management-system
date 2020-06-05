# Article Management System Tutorial
Spring Framework - AMS API and Application 

## Prerequisite
Before getting into the tutorial, please make sure you have the following requirements on your computer:
- Installing Java Version 8 or later
- IDE: IntelliJ or Spring Tool Suite (This tutorial using IntelliJ)
- Database: PostgreSQL on local or Server (Backup the database file that is located in folder _src/main/resources/database_)

## Create Spring Boot Project
- Open IntelliJ IDE and you will see screen as below, after that choose __Create New Project__

<p align="center">
  <img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.jpg?alt=media&token=6918381b-90d4-4b15-a116-e34db21bb9b8" width="600" height="400" />
</p>

- Select __Spring Initializr__ for Spring Boot Project -> Next

<p align="center">
<img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.-1jpg.JPG?alt=media&token=fa043697-40f7-4c96-8878-853597ce6055" width="600" height="400" />
</p>

- Enter and modifty __Spring Initializr Project Settings__ (Sample in screen below) -> Next

<p align="center">
<img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.-2jpg.JPG?alt=media&token=31e9b1de-0fe0-42d0-a1f9-e9808be3cb2e" width="600" height="400" />
</p>

- Next Screen (Keep Default) -> Next
- New Project
  - Project name: enter your project name
  - Project location: choose the location that you want to store your project
- Finish
- Your project is created as screen below

<p align="center">
<img align="center" src="https://firebasestorage.googleapis.com/v0/b/fir-demo-b5359.appspot.com/o/pictures%2Fintellij-start-screen.-3jpg.JPG?alt=media&token=0a5cd952-0875-4392-9f6d-3aa0fb89d5ca" width="600" height="400" />
</p>

## Project Structure

Suppose you have the _com.example.ams_ package under _src/main/java_ folder in your project structure, then create sub-packages under the package _com.example.ams_ (Right click on _com.example.ams_ -> New -> Package):
- configuration
- model
- repository
- service
- controller

## Maven Dependencies

Before proceed further, you have to add some dependencies under __dependencies__ section in _pom.xml_ file:
- postgresql: Driver for connection to PostgreSQL DB
- mybatis: Object relational mapping (ORM)
- nekohtml & spring-boot-devtools (optional)

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
<dependency>
    <groupId>net.sourceforge.nekohtml</groupId>
    <artifactId>nekohtml</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

You can find other maven dependencies in this website ==> https://mvnrepository.com/

