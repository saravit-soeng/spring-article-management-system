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
