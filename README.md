# Article Management System
Spring Framework - Article Management System (AMS) API and Application 

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

## Add Maven Dependencies

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
- __Pagination__

```java
package com.example.ams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {
    private int page;
    private int limit;
    @JsonProperty("total_page")
    private int totalPage;
    @JsonProperty("total_record")
    private int totalRecord;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
```

## Create RESTful API for AMS

- __Category REST Endpoint__

Under __repository__ package -> create new java class -> interface type -> name it "CategoryRepo"

```java
package com.example.ams.repository;

import com.example.ams.model.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo {

    @Select("SELECT * FROM category ORDER BY id")
    List<Category> getAllCategories();

    @Insert("INSERT INTO category(name) VALUES(#{name})")
    Boolean addCategory(Category category);

    @Update("UPDATE category set name=#{name} WHERE id=#{id}")
    Boolean updateCategory(Category category);
}
```

After created the repository, now created category service. Under __service__ package -> Create new java class -> interface type -> "CategoryService"

```java
package com.example.ams.service;

import com.example.ams.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Boolean addCategory(Category category);
    Boolean updateCategory(Category category);
}
```
Now you need to implement the CategoryService to provide business logic, under service package -> create a new package -> name it "impl". Then, under __impl__ package -> Create new java class -> "CategoryServiceImpl"

```java
package com.example.ams.service.impl;

import com.example.ams.model.Category;
import com.example.ams.repository.CategoryRepo;
import com.example.ams.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.getAllCategories();
    }

    @Override
    public Boolean addCategory(Category category) {
        return categoryRepo.addCategory(category);
    }

    @Override
    public Boolean updateCategory(Category category) {
        return categoryRepo.updateCategory(category);
    }
}
```

Last step is to create Controller. Now, under __controller__ package -> Create new package -> name the package to "restapi".

Under __restapi__ package -> Create new java class -> "CategoryController"

```java
package com.example.ams.controller.restapi;

import com.example.ams.model.Category;
import com.example.ams.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<Map<String,Object>> getAllCategories(){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Category> categories = categoryService.getAllCategories();
            map.put("message", "success");
            map.put("status", true);
            map.put("data", categories);
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Map<String,Object>> addCategory(@RequestBody Category category){
        Map<String, Object> map = new HashMap<>();
        try {
            boolean isAdded = categoryService.addCategory(category);
            if(isAdded){
                map.put("message", "success");
                map.put("status", true);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<Map<String,Object>> updateCategory(@RequestBody Category category){
        Map<String, Object> map = new HashMap<>();
        try {
            boolean isUpdated = categoryService.updateCategory(category);
            if(isUpdated){
                map.put("message", "success");
                map.put("status", true);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
```

==> Now you have created Category REST API Endpoint. 🙂

- __Article REST Endpoint__

Before getting further, create two more models for RequestBody in Model Schema. Under __model__ package -> create new package -> name it to "schema"

- AddedArticle

```java
package com.example.ams.model.schema;

public class AddedArticle {
    private String title;
    private String description;
    private String thumbnail;
    private int category_id;
    private String author;

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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
```

- UpdatedArticle

```java
package com.example.ams.model.schema;

public class UpdatedArticle {
    private int id;
    private String title;
    private String description;
    private String thumbnail;
    private int category_id;
    private String author;

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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
```

Now let's create Article Endpoint, steps and flow are the same as Category endpoint:

Under __repository__ -> create ArticleRepo

```java
package com.example.ams.repository;

import com.example.ams.model.Article;
import com.example.ams.model.schema.AddedArticle;
import com.example.ams.model.schema.UpdatedArticle;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepo {

    @Insert("INSERT INTO article(title, description, thumbnail, category_id, author) VALUES(#{title},#{description},#{thumbnail}, #{category_id}, #{author})")
    Boolean addArticle(AddedArticle article);

    @Update("UPDATE article SET title=#{title}, description=#{description}, thumbnail=#{thumbnail}, category_id=#{category_id}, author=#{author} WHERE id=#{id}")
    Boolean updateArticle(UpdatedArticle article);

    @Delete("DELETE FROM article WHERE id=#{id}")
    Boolean deleteArticle(int id);

    @Select("select " +
            "a.id, a.title, a.description, a.thumbnail, a.author, a.created_date, a.category_id, c.name as category_name " +
            "from article a inner join category c on a.category_id = c.id order by a.id desc limit #{limit} offset #{offset}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "thumbnail", column = "thumbnail"),
            @Result(property = "author", column = "author"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.name", column = "category_name"),
            @Result(property = "createdDate", column = "created_date")
    })
    List<Article> getAllArticles(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from article")
    int countAllArticles();

    @Select("select " +
            "a.id, a.title, a.description, a.thumbnail, a.author, a.created_date, a.category_id, c.name as category_name " +
            "from article a inner join category c on a.category_id = c.id where a.id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "thumbnail", column = "thumbnail"),
            @Result(property = "author", column = "author"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.name", column = "category_name"),
            @Result(property = "createdDate", column = "created_date")
    })
    Article getArticleByID(int id);

    @Select("select " +
            "a.id, a.title, a.description, a.thumbnail, a.author, a.created_date, a.category_id, c.name as category_name " +
            "from article a inner join category c on a.category_id = c.id where a.category_id=#{category_id} order by a.id desc limit #{limit} offset #{offset}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "thumbnail", column = "thumbnail"),
            @Result(property = "author", column = "author"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.name", column = "category_name"),
            @Result(property = "createdDate", column = "created_date")
    })
    List<Article> getArticlesByCategory(@Param("category_id") int categoryID, @Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from article where category_id=#{category_id}")
    int countArticleByCategory(@Param("category_id") int categoryID);
}
```

Under __service__ -> create ArticleService interface

```java
package com.example.ams.service;

import com.example.ams.model.Article;
import com.example.ams.model.Pagination;
import com.example.ams.model.schema.AddedArticle;
import com.example.ams.model.schema.UpdatedArticle;
import java.util.List;

public interface ArticleService {
    Boolean addArticle(AddedArticle article);
    Boolean updateArticle(UpdatedArticle article);
    Boolean deleteArticle(int id);
    List<Article> getAllArticles(int page, int limit);
    Pagination getPagination(int page, int limit);
    Article getArticleByID(int id);
    List<Article> getArticlesByCategory(int categoryID, int page, int limit);
    Pagination getPaginationByCategory(int categoryID, int page, int limit);
}
```

Under __service__ -> __impl__ -> create ArticleServiceImpl class

```java
package com.example.ams.service.impl;

import com.example.ams.model.Article;
import com.example.ams.model.Pagination;
import com.example.ams.model.schema.AddedArticle;
import com.example.ams.model.schema.UpdatedArticle;
import com.example.ams.repository.ArticleRepo;
import com.example.ams.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public Boolean addArticle(AddedArticle article) {
        return articleRepo.addArticle(article);
    }

    @Override
    public Boolean updateArticle(UpdatedArticle article) {
        return articleRepo.updateArticle(article);
    }

    @Override
    public Boolean deleteArticle(int id) {
        return articleRepo.deleteArticle(id);
    }

    @Override
    public List<Article> getAllArticles(int page, int limit) {
        int offset = (page - 1) * limit;
        return articleRepo.getAllArticles(offset, limit);
    }

    @Override
    public Pagination getPagination(int page, int limit) {
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setLimit(limit);
        int totalRecords = articleRepo.countAllArticles();
        pagination.setTotalRecord(totalRecords);
        int totalPage = (int) Math.ceil( (double)totalRecords / limit);
        pagination.setTotalPage(totalPage);
        return pagination;
    }

    @Override
    public Article getArticleByID(int id) {
        return articleRepo.getArticleByID(id);
    }

    @Override
    public List<Article> getArticlesByCategory(int categoryID, int page, int limit) {
        int offset = (page - 1) * limit;
        return articleRepo.getArticlesByCategory(categoryID, offset, limit);
    }

    @Override
    public Pagination getPaginationByCategory(int categoryID, int page, int limit) {
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setLimit(limit);
        int totalRecords = articleRepo.countArticleByCategory(categoryID);
        pagination.setTotalRecord(totalRecords);
        int totalPage = (int) Math.ceil( (double)totalRecords / limit);
        pagination.setTotalPage(totalPage);
        return pagination;
    }
}
```

Under __controller__ -> __restapi__ -> create ArticleController class

```java
package com.example.ams.controller.restapi;

import com.example.ams.model.Article;
import com.example.ams.model.Pagination;
import com.example.ams.model.schema.AddedArticle;
import com.example.ams.model.schema.UpdatedArticle;
import com.example.ams.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<Map<String,Object>> addArticle(@RequestBody AddedArticle article){
        Map<String, Object> map = new HashMap<>();
        try {
            boolean isAdded = articleService.addArticle(article);
            if(isAdded){
                map.put("message", "success");
                map.put("status", true);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/article")
    public ResponseEntity<Map<String,Object>> updateArticle(@RequestBody UpdatedArticle article){
        Map<String, Object> map = new HashMap<>();
        try {
            boolean isUpdated = articleService.updateArticle(article);
            if(isUpdated){
                map.put("message", "success");
                map.put("status", true);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Map<String,Object>> updateArticle(@PathVariable("id") int id){
        Map<String, Object> map = new HashMap<>();
        try {
            boolean isUpdated = articleService.deleteArticle(id);
            if(isUpdated){
                map.put("message", "success");
                map.put("status", true);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/article")
    public ResponseEntity<Map<String,Object>> getArticles(@RequestParam(value = "page", defaultValue = "1", required = false) int page, @RequestParam(value = "limit", defaultValue = "15", required = false) int limit){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Article> articles = articleService.getAllArticles(page, limit);
            Pagination pagination = articleService.getPagination(page, limit);
            if(articles != null){
                map.put("message", "success");
                map.put("status", true);
                map.put("pagination", pagination);
                map.put("data", articles);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Map<String,Object>> getArticles(@PathVariable("id") int id){
        Map<String, Object> map = new HashMap<>();
        try {
            Article article = articleService.getArticleByID(id);
            if(article != null){
                map.put("message", "success");
                map.put("status", true);
                map.put("data", article);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/article-by-category/{category_id}")
    public ResponseEntity<Map<String,Object>> getArticlesByCategory(@PathVariable("category_id") int categoryID ,@RequestParam(value = "page", defaultValue = "1", required = false) int page, @RequestParam(value = "limit", defaultValue = "15", required = false) int limit){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Article> articles = articleService.getArticlesByCategory(categoryID, page, limit);
            Pagination pagination = articleService.getPaginationByCategory(categoryID, page, limit);
            if(articles != null){
                map.put("message", "success");
                map.put("status", true);
                map.put("pagination", pagination);
                map.put("data", articles);
            }else {
                map.put("message", "fail");
                map.put("status", false);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("message", "error");
            map.put("status", false);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
```

Now you have created REST API endpoint for article. 

- __Create File Upload REST Endpoint__

In __application.properties__, add new properties for path configuration.

```
file.upload.server.path=C:/Users/Saravit/Documents/upload/
file.upload.client.path=/image/
url.path=http://localhost:8080
```

Create __FileUploadConfiguration__ class in __configuration__ package

```java
package com.example.ams.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileUploadConfiguration implements WebMvcConfigurer {

    @Value("${file.upload.client.path}")
    private String clientPath;

    @Value("${file.upload.server.path}")
    private String serverPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientPath + "**").addResourceLocations("file:"+serverPath);
    }
}
```

Under __service__ -> create __FileUploadService__ interface

```java
package com.example.ams.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String upload(MultipartFile file);
}
```

Under __service__ -> __impl__ -> create __FileUploadServiceImpl__ class

```java
package com.example.ams.service.impl;

import com.example.ams.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.upload.client.path}")
    private String clientPath;

    @Value("${file.upload.server.path}")
    private String serverPath;

    @Override
    public String upload(MultipartFile file) {
        if (file == null) {
            return null;
        }

        File path = new File(this.serverPath);
        if(!path.exists()) path.mkdirs();

        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".")+1);

        try{
            Files.copy(file.getInputStream(), Paths.get(this.serverPath, filename));
        }catch (Exception e){
            e.printStackTrace();
        }

        return  this.clientPath + filename;
    }
}
```

Under __controller__ -> __restapi__ -> create __FileUploadRestController__ class

```java
package com.example.ams.controller.restapi;

import com.example.ams.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileUploadRestController {

    @Value("${url.path}")
    private String urlPath;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Map<String, Object> uploadImage(MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        map.put("message", "upload success");
        map.put("data", urlPath + fileUploadService.upload(file));
        return map;
    }
}
```

If you are here, you have created AMS API. 👏👏👏

## Configure Basic Security for API

In this tutorial, I just created the basic in-memory authentication.

First, you add security dependency in _pom.xml_ file.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Now let's create __APISecurityConfiguration__ class in __configuration__ package:

```java
package com.example.ams.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class APISecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("apiuser")
                .password("{noop}api@1234") // {noop} is the password encryption pattern in new version of Spring Boot
                .roles("API");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/**", "/api-docs").hasRole("API");
    }
}
```

## Create API Document with Swagger

In this example, i used Swagger v2. Now, let's add swagger dependcy to _pom.xml_ file:

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.4.0</version>
</dependency>
```

Under __configuration__ -> create __SwaggerConfiguration__ class:

```java
package com.example.ams.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ams.controller.restapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    @SuppressWarnings("deprecation")
    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfo("AMS API Documentation",
                "API for Article Management",
                "1.0",
                "http://www.example.com",
                "XYZ",
                "http://www.example.com",
                "http://www.example.com");
    }

    @Configuration
    public static class SwaggerCofig implements WebMvcConfigurer{
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/api-docs").setViewName("swagger/index");
        }
    }
}
```

As we will custom the swagger ui, now download the swagger ui from https://github.com/swagger-api/swagger-ui/tree/2.x/

Anyway, as we will use Thymeleaf as our template engine, now let's add thymeleaf dependency in _pom.xml_ file 

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
After that, add these properties to __application.properties__ file:

```
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML
```

Under __resources/static__ folder, create new folder "swagger" and then copy all folders and files (except __index.html__ file) from __dist__ folder in swagger ui downloaded folder above to this __swagger__ folder.

Under __resources/templates__, create new folder "swagger" and then copy __index.html__ file from __dist__ folder in swagger ui downloaded folder above to this __swagger__ folder.

Now, add and modify code in the __index.html__ as the sample below:

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="x-ua-compatible" content="IE=edge">
  <title>AMS API</title>
  <link rel="icon" type="image/png" href="/swagger/images/favicon-32x32.png" sizes="32x32" />
  <link rel="icon" type="image/png" href="/swagger/images/favicon-16x16.png" sizes="16x16" />
  <link href='/swagger/css/typography.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='/swagger/css/reset.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='/swagger/css/screen.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='/swagger/css/reset.css' media='print' rel='stylesheet' type='text/css'/>
  <link href='/swagger/css/print.css' media='print' rel='stylesheet' type='text/css'/>
  <script src='/swagger/lib/object-assign-pollyfill.js' type='text/javascript'></script>
  <script src='/swagger/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
  <script src='/swagger/lib/jquery.slideto.min.js' type='text/javascript'></script>
  <script src='/swagger/lib/jquery.wiggle.min.js' type='text/javascript'></script>
  <script src='/swagger/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
  <script src='/swagger/lib/handlebars-4.0.5.js' type='text/javascript'></script>
  <script src='/swagger/lib/lodash.min.js' type='text/javascript'></script>
  <script src='/swagger/lib/backbone-min.js' type='text/javascript'></script>
  <script src='/swagger/swagger-ui.js' type='text/javascript'></script>
  <script src='/swagger/lib/highlight.9.1.0.pack.js' type='text/javascript'></script>
  <script src='/swagger/lib/highlight.9.1.0.pack_extended.js' type='text/javascript'></script>
  <script src='/swagger/lib/jsoneditor.min.js' type='text/javascript'></script>
  <script src='/swagger/lib/marked.js' type='text/javascript'></script>
  <script src='/swagger/lib/swagger-oauth.js' type='text/javascript'></script>

  <!-- Some basic translations -->
  <!-- <script src='lang/translator.js' type='text/javascript'></script> -->
  <!-- <script src='lang/ru.js' type='text/javascript'></script> -->
  <!-- <script src='lang/en.js' type='text/javascript'></script> -->

  <script type="text/javascript">
    $(function () {
      var url = window.location.search.match(/url=([^&]+)/);
      if (url && url.length > 1) {
        url = decodeURIComponent(url[1]);
      } else {
        url = "/v2/api-docs";
      }

      hljs.configure({
        highlightSizeThreshold: 5000
      });

      // Pre load translate...
      if(window.SwaggerTranslator) {
        window.SwaggerTranslator.translate();
      }
      window.swaggerUi = new SwaggerUi({
        url: url,
        dom_id: "swagger-ui-container",
        supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
        onComplete: function(swaggerApi, swaggerUi){
          if(typeof initOAuth == "function") {
            initOAuth({
              clientId: "your-client-id",
              clientSecret: "your-client-secret-if-required",
              realm: "your-realms",
              appName: "your-app-name",
              scopeSeparator: " ",
              additionalQueryStringParams: {}
            });
          }

          if(window.SwaggerTranslator) {
            window.SwaggerTranslator.translate();
          }

          addApiKeyAuthorization();
        },
        onFailure: function(data) {
          log("Unable to Load SwaggerUI");
        },
        docExpansion: "none",
        jsonEditor: false,
        defaultModelRendering: 'schema',
        showRequestHeaders: false,
        showOperationIds: false
      });

      function addApiKeyAuthorization(){
        var key = "Basic YXBpdXNlcjphcGlAMTIzNA=="; //encodeURIComponent($('#input_apiKey')[0].value);
        if(key && key.trim() != "") {
          var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization("Authorization", key, "header");
          window.swaggerUi.api.clientAuthorizations.add("api_key", apiKeyAuth);
          log("added key " + key);
        }
      }

      $('#input_apiKey').change(addApiKeyAuthorization);

      window.swaggerUi.load();

      function log() {
        if ('console' in window) {
          console.log.apply(console, arguments);
        }
      }
  });
  </script>
</head>

<body class="swagger-section">
<div id='header'>
  <div class="swagger-ui-wrap">
    <a id="logo" href="http://swagger.io"><img class="logo__img" alt="swagger" height="30" width="30" src="/swagger/images/article_symbol.png" /><span class="logo__title">AMS API</span></a>
    <form id='api_selector'>
      <div class='input'><input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="hidden"/></div>
      <div class='input'><input placeholder="api_key" id="input_apiKey" name="apiKey" type="text" value="Basic YXBpdXNlcjphcGlAMTIzNA=="/></div>
    </form>
  </div>
</div>

<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
```

Now all is done for Swagger configuration. 

**Attention**
pleas add this property to __application.properties__ to fix the timezone issue with timestamp datatype

```
spring.jackson.time-zone=Asia/Seoul
```
--> For Cambodia: Asia/Phnom_Penh





