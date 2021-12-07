package com.example.webclient.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {
    private final Long id;
    private final String nom;
    private final LocalDate dob;
}
