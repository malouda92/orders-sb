package com.example.orders.services;

import com.example.orders.dto.ClientDTO;
import com.example.orders.mapper.ClientMapper;
import com.example.orders.models.Client;
import com.example.orders.repositories.ClientRepository;
import com.example.orders.utils.Constants;
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
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        return clientMapper.toClientDto(clientRepository.save(clientMapper.toClient(clientDTO)));
    }

    @Transactional
    public ClientDTO modifyClient(Long id, String nom, LocalDate dob) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            if (nom != null && !nom.isEmpty()) {
                clientOptional.get().setNom(nom);
            }
            if (dob != null) {
                clientOptional.get().setDob(dob);
            }
            return clientMapper.toClientDto(clientOptional.get());
        }else {
            throw new IllegalStateException(Constants.CLIENT_NOT_FOUND);
        }

    }

    @Override
    public ClientDTO getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent())  {
            return clientMapper.toClientDto(clientOptional.get());
        }else {
            throw new IllegalStateException(Constants.CLIENT_NOT_FOUND);
        }
    }

    @Override
    public List<ClientDTO> getAllClient() {
        return clientMapper.toClientDtos(clientRepository.findAll());
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalStateException(Constants.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }
}
