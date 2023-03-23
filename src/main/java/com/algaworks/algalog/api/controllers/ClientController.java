package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @PersistenceContext // Injeta um EntityManager na variável manager
    private EntityManager manager;

    @GetMapping("/clients")
    public List<Client> list() {
        return manager.createQuery("from Client", Client.class)
                .getResultList();
    }
}
