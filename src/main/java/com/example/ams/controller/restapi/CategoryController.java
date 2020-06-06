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