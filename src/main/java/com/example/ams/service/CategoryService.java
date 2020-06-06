package com.example.ams.service;

import com.example.ams.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Boolean addCategory(Category category);
    Boolean updateCategory(Category category);
}
