package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.api.mapper.DeliveryMapper;
import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInputModel;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.service.DeliveryCompletionService;
import com.algaworks.algalog.domain.service.RequestDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private DeliveryRepository deliveryRepository;
    private RequestDeliveryService requestDeliveryService;
    private DeliveryCompletionService deliveryCompletionService;
    private DeliveryMapper deliveryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryModel request(@Valid @RequestBody DeliveryInputModel deliveryInputModel) {
        // OBS importante: A conversão deve ser feita aqui no controller, o service não pode conhecer classes da camada da API

        // Recebe um objeto do tipo DeliveryInputModel e mapeia para entidade Delivery
        Delivery newDelivery = deliveryMapper.toEntity(deliveryInputModel);
        // Chama o service que retorna uma Delivery
        Delivery requestedDelivery = requestDeliveryService.request(newDelivery);

        // Mapeia para DeliveryModel
        return deliveryMapper.toModel(requestedDelivery);
    }

    @GetMapping
    public List<DeliveryModel> list() {
        return deliveryMapper.toCollectionModel(deliveryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryModel> findById(@PathVariable Long id) {
        return deliveryRepository.findById(id)
                .map(delivery -> ResponseEntity.ok(deliveryMapper.toModel(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        deliveryCompletionService.finish(id);
    }
}
