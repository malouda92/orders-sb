package com.example.orders.services;

import com.example.orders.dto.ArticleDTO;
import com.example.orders.mapper.ArticleMapper;
import com.example.orders.models.Article;
import com.example.orders.repositories.ArticleRepository;
import com.example.orders.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        return articleMapper.toArticleDTO(articleRepository.save(articleMapper.toArticle(articleDTO)));
    }

    @Override
    public ArticleDTO modifyArticle(Long id, String libelle, Double price) {
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isPresent()) {
            if (libelle != null && libelle.length() > 0) {
                articleOptional.get().setLibelle(libelle);
            }
            if (price != null) {
                articleOptional.get().setPrice(price);
            }
            return articleMapper.toArticleDTO(articleOptional.get());
        }else {
            throw new IllegalStateException(Constants.ARTICLE_NOT_FOUND);
        }
    }

    @Override
    public List<ArticleDTO> getAllArticle() {
        return articleMapper.toArticleDTOs(articleRepository.findAll());
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            return articleMapper.toArticleDTO(articleOptional.get());
        }else {
            throw new IllegalStateException(Constants.ARTICLE_NOT_FOUND);
        }
    }

    @Override
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalStateException(Constants.ARTICLE_NOT_FOUND);
        }
        articleRepository.deleteById(id);
    }
}
