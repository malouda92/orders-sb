package com.example.orders.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class OrderDTO {
    private final Long id;
    private final LocalDate orderDate;
    private final ClientDTO orderClient;
    private final Collection<ArticleDTO> articleOrder = new ArrayList<>();
}
