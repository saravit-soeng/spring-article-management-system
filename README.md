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

## Adding Maven Dependencies

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

Save the pom.xml file and right click on the project -> Maven -> Reimport . You can find other maven dependencies in this website ==> https://mvnrepository.com/

## Create Datasource

Open __application.properties__ file and add the following properties for create datasource to connect to database

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/ams
spring.datasource.username=postgres
spring.datasource.password=1234
```

In the example above, I have been using PostgreSQL datasource. You can modify or change to your own like url, username, or password.

## Configure MyBatis

Under the __configuration__ package, now create new __Java Class__ and give its name to _MyBatisConfiguration_. The _MyBatisConfiguration_ class look like below:

```java
package com.example.ams.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.ams.repository")
public class MyBatisConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}
```

## Create Model

Under the __model__ package, create 3 models (Category, Article, Pagination)

- __Category__
```java
public class Category {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
- __Article__
```java
package com.example.ams.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

public class Article {

    private int id;
    private String title;
    private String description;
    private String thumbnail;
    private Category category;
    private String author;
    @JsonProperty(value = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category=" + category +
                ", author='" + author + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
```

