package com.example.webclient.dto;

import lombok.Data;

@Data
public class ArticleDTO {
    private final Long id;
    private final String libelle;
    private final Double price;
}
