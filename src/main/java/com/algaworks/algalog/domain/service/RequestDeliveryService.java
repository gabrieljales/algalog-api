// OBS1: Esse serviço poderia ser mais genérico e conter mais métodos, como todos os métodos de CRUD. Isso fica a critério do time
// OBS2: Veja que aqui estamos usando a linguagem de domínio, ou seja, invés de ser "CreateDeliveryService", usamos uma linguagem que os usuários usam com "RequestDeliveryService" (solicitação de entrega)
package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RequestDeliveryService {
    private ClientCatalogService clientCatalogService;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery request(Delivery delivery) {
        // OBS: Nesse caso o mais indicado é um erro 400 invés de um 404, já que houve um erro ao passar um cliente inexistente no corpo
        Client client = clientCatalogService.findById(delivery.getClient().getId());

        // Propriedades calculadas (não são informadas pelo usuário)
        delivery.setClient(client); // Evita de retornar o cliente com propriedades vazias e somente o ID preenchido
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }
}
