package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.RecipientModel;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery request(@Valid @RequestBody Delivery delivery) {
        return requestDeliveryService.request(delivery);
    }

    @GetMapping
    public List<Delivery> list() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryModel> findById(@PathVariable Long id) {
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    DeliveryModel deliveryModel = new DeliveryModel();
                    deliveryModel.setId(delivery.getId());
                    deliveryModel.setClientName(delivery.getClient().getName());
                    deliveryModel.setRecipient(new RecipientModel());
                    deliveryModel.getRecipient().setName(delivery.getRecipient().getName());
                    deliveryModel.getRecipient().setAddress(delivery.getRecipient().getAddress());
                    deliveryModel.getRecipient().setComplement(delivery.getRecipient().getComplement());
                    deliveryModel.getRecipient().setNumber(delivery.getRecipient().getNumber());
                    deliveryModel.getRecipient().setDistrict(delivery.getRecipient().getDistrict());
                    deliveryModel.setTax(delivery.getTax());
                    deliveryModel.setStatus(delivery.getStatus());
                    deliveryModel.setOrderDate(delivery.getOrderDate());
                    deliveryModel.setDueDate(delivery.getDueDate());

                    return ResponseEntity.ok(deliveryModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
