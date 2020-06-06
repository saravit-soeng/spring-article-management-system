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