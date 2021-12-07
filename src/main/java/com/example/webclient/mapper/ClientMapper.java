package com.example.webclient.mapper;

import com.example.webclient.dto.ClientDTO;
import com.example.webclient.models.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toClientDto(Client client);
    List<ClientDTO> toClientDtos(List<Client> clients);
    Client toClient(ClientDTO clientDTO);
}
