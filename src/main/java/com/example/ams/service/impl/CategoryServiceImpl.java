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
