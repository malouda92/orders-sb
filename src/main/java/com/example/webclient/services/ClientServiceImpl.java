package com.example.webclient.services;

import com.example.webclient.models.Client;
import com.example.webclient.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client modifyClient(Long id, String nom, LocalDate dob) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            if (nom != null && !nom.isEmpty()) {
                clientOptional.get().setNom(nom);
            }
            if (dob != null) {
                clientOptional.get().setDob(dob);
            }
            return clientOptional.get();
        }else {
            throw new IllegalStateException("le client n'existe pas");
        }

    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalStateException("Le client n'existe pas");
        }
        clientRepository.deleteById(id);
    }
}
