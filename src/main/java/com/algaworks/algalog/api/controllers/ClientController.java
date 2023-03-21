package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/clients")
    public List<Client> list() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("João");
        client1.setPhone("34 99999-1111");
        client1.setEmail("joao@algalog.com");

        // TODO: Parei no vídeo 1 do capítulo 2, aos 7 minutos

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Maria");
        client2.setPhone("34 93333-1222");
        client2.setEmail("maria@algalog.com");
        return Arrays.asList(client1, client2);
    }
}
