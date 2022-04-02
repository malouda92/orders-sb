package com.example.orders.services;

import com.example.orders.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    ArticleDTO createArticle(ArticleDTO articleDTO);
    ArticleDTO modifyArticle(Long id, String libelle, Double price);
    List<ArticleDTO> getAllArticle();
    ArticleDTO getArticleById(Long id);
    void deleteArticle(Long id);
}
