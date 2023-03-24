package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor // Gerando um construtor com todas as propriedas
@RestController
public class ClientController {

    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> list() {
        return clientRepository.findAll();
    }
}
