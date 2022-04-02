package com.example.orders.mapper;

import com.example.orders.dto.ClientDTO;
import com.example.orders.models.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toClientDto(Client client);
    List<ClientDTO> toClientDtos(List<Client> clients);
    Client toClient(ClientDTO clientDTO);
}
