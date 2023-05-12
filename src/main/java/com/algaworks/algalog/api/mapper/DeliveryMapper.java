package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInputModel;
import com.algaworks.algalog.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DeliveryMapper {

    private ModelMapper modelMapper;

    public DeliveryModel toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryModel.class);
    }

    public List<DeliveryModel> toCollectionModel(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryInputModel deliveryInputModel) {
        return modelMapper.map(deliveryInputModel, Delivery.class);
    }
}
