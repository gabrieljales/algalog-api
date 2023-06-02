package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DeliveryCompletionService {

    private DeliveryRepository deliveryRepository;
    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public void finish(Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        // DICA: Veja que a linha abaixo dexa o código muito mas legível do quê fazer a regra de negócio dentro desse service
        // LEMBRE-SE: Sempre que puder, evite Entidades anêmicas! (com poucas regras de negócio definidas nelas)
        delivery.finish();

        deliveryRepository.save(delivery);
    }
}
