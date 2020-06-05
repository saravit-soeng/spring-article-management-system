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
