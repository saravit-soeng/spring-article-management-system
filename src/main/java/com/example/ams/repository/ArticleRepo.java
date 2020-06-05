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
