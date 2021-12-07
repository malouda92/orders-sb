package com.example.webclient.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private final Long id;
    private final LocalDate orderDate;
    private final ClientDTO orderClient;
}
