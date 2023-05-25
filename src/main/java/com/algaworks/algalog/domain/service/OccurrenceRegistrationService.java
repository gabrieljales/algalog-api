package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Occurrence;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OccurrenceRegistrationService {

    private SearchDeliveryService searchDeliveryService;

    // Assim que a transação for executada com sucesso, o jakarta persistence vai sincronizar automaticamente sem precisar chamar o método save()
    @Transactional
    public Occurrence register(Long deliveryId, String description) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        return delivery.addOccurrence(description);
    }
}
