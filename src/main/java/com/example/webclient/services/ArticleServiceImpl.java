package com.example.webclient.services;

import com.example.webclient.models.Article;
import com.example.webclient.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article modifyArticle(Long id, String libelle, Double price) {
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isPresent()) {
            if (libelle != null && libelle.length() > 0) {
                articleOptional.get().setLibelle(libelle);
            }
            if (price != null) {
                articleOptional.get().setPrice(price);
            }
            return articleOptional.get();
        }else {
            throw new IllegalStateException("L'Article n'existe pas");
        }
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalStateException("L'Article n'existe pas");
        }
        articleRepository.deleteById(id);
    }
}
