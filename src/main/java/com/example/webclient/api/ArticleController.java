package com.example.webclient.api;

import com.example.webclient.models.Article;
import com.example.webclient.services.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @GetMapping("/article")
    public List<Article> getAllArticle() {
        return articleService.getAllArticle();
    }
}
