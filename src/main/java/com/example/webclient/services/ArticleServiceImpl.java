package com.example.webclient.services;

import com.example.webclient.models.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.example.webclient.utils.Constants.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Override
    public Article createArticle(Article article) {
        return null;
    }

    @Override
    public Article modifyArticle(Long id, String libelle, Double price) {
        return null;
    }

    @Override
    public List<Article> getAllArticle() {
        return this.getWebClient().get()
                .uri("/article")
                .retrieve()
                .bodyToFlux(Article.class)
                .collectList()
                .block();
    }

    @Override
    public Article getArticleById(Long id) {
        return null;
    }

    @Override
    public void deleteArticle(Long id) {

    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.create(BASE_URL);
    }
}
