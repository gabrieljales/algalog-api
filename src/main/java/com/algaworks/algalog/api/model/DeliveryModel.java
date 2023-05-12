package com.algaworks.algalog.api.model;

import com.algaworks.algalog.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryModel {
    private Long id;
    private ClientOverviewModel client;
    private RecipientModel recipient;
    private BigDecimal tax;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime dueDate;
}
