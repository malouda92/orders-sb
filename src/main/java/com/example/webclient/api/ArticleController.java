package com.example.webclient.api;

import com.example.webclient.dto.ArticleDTO;
import com.example.webclient.mapper.ArticleMapper;
import com.example.webclient.services.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleServiceImpl articleService;
    private final ArticleMapper articleMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_ARTICLE')")
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticle() {
        return ResponseEntity.ok(articleMapper.toArticleDTOs(articleService.getAllArticle()));
    }

    @PreAuthorize("hasAuthority('ARTICLE_WRITE')")
    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleMapper.toArticleDTO(articleService.createArticle(articleMapper.toArticle(articleDTO))));
    }

    @PreAuthorize("hasAuthority('ARTICLE_WRITE')")
    @PutMapping(path = "{id}")
    public ResponseEntity<ArticleDTO> modifyArticle(@PathVariable Long id,
                                                    @RequestParam(required = false) String libelle,
                                                    @RequestParam(required = false) Double price) {
        return ResponseEntity.ok(articleMapper.toArticleDTO(articleService.modifyArticle(id, libelle, price)));
    }

    @PreAuthorize("hasAuthority('ARTICLE_WRITE')")
    @DeleteMapping(path = "{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
