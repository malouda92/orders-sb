package com.example.webclient.services;

import com.example.webclient.models.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);
    Client modifyClient(Long id, String nom, LocalDate dob);
    Optional<Client> getClientById(Long id);
    List<Client> getAllClient();
    void deleteClient(Long id);
}
