package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.api.mapper.OccurrenceMapper;
import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.api.model.input.OccurrenceInputModel;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Occurrence;
import com.algaworks.algalog.domain.service.OccurrenceRegistrationService;
import com.algaworks.algalog.domain.service.SearchDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

    private SearchDeliveryService searchDeliveryService;
    private OccurrenceRegistrationService occurrenceRegistrationService;
    private OccurrenceMapper occurrenceMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceModel register (@PathVariable Long deliveryId, @Valid @RequestBody OccurrenceInputModel occurrenceInputModel) {
        Occurrence registeredOccurrence = occurrenceRegistrationService.register(deliveryId, occurrenceInputModel.getDescription());

        return occurrenceMapper.toModel(registeredOccurrence);
    }

    @GetMapping
    public List<OccurrenceModel> list(@PathVariable Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        return occurrenceMapper.toCollectionModel(delivery.getOccurrences());
    }
}
