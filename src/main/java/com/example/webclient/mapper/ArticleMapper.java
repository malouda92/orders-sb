package com.example.webclient.mapper;

import com.example.webclient.dto.ArticleDTO;
import com.example.webclient.models.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO toArticleDTO(Article article);
    List<ArticleDTO> toArticleDTOs(List<Article> articles);
    Article toArticle(ArticleDTO articleDTO);
}
