package com.example.webclient.api;


import com.example.webclient.dto.ClientDTO;
import com.example.webclient.mapper.ClientMapper;
import com.example.webclient.services.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientServiceImpl clientService;
    private final ClientMapper clientMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_CLIENT')")
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClient() {
        return ResponseEntity.ok(clientMapper.toClientDtos(clientService.getAllClient()));
    }

    @PreAuthorize("hasAuthority('CLIENT_WRITE')")
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toClientDto(clientService.createClient(clientMapper.toClient(clientDTO))));
    }

    @PreAuthorize("hasAuthority('CLIENT_WRITE')")
    @PutMapping(path = "{id}")
    public ResponseEntity<ClientDTO> modifyClient(@PathVariable("id") Long id,
                               @RequestParam(required = false) String nom,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dob) {
        return ResponseEntity.ok(clientMapper.toClientDto(clientService.modifyClient(id, nom, dob)));
    }

    @DeleteMapping(path = "{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
