package com.example.orders.mapper;

import com.example.orders.dto.ArticleDTO;
import com.example.orders.models.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO toArticleDTO(Article article);
    List<ArticleDTO> toArticleDTOs(List<Article> articles);
    Article toArticle(ArticleDTO articleDTO);
}
