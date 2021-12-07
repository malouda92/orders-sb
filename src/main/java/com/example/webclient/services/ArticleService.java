package com.example.webclient.services;

import com.example.webclient.models.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article article);
    Article modifyArticle(Long id, String libelle, Double price);
    List<Article> getAllArticle();
    Article getArticleById(Long id);
    void deleteArticle(Long id);
}
