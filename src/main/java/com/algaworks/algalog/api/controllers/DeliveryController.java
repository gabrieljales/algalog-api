package com.algaworks.algalog.api.controllers;

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
    public ResponseEntity<Delivery> findById(@PathVariable Long id) {
        return deliveryRepository.findById(id)
                .map(ResponseEntity::ok) // Essa linha é a mesma coisa que: .map(delivery -> ResponseEntity.ok(delivery))
                .orElse(ResponseEntity.notFound().build());
    }
}
