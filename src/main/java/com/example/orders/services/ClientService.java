package com.example.orders.services;

import com.example.orders.dto.ClientDTO;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO modifyClient(Long id, String nom, LocalDate dob);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClient();
    void deleteClient(Long id);
}
