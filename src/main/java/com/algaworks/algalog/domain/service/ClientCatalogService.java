package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientCatalogService {
    private ClientRepository clientRepository;

    public Client findById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));
    }

    @Transactional // Declara que esse método deve ser executado dentro de uma transação
    public Client save(Client client) {
        boolean usedEmail = clientRepository.findByEmail(client.getEmail())
                .stream()
                .anyMatch(existentClient -> !existentClient.equals(client)); // Verifica se o cliente encontrado é diferente do cliente que será salvo

        if (usedEmail) {
            throw new DomainException("Já existe um cliente cadastrado com esse e-mail");
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
