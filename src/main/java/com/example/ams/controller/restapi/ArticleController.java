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
