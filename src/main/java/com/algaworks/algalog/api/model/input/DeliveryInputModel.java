package com.algaworks.algalog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInputModel {
    @Valid
    @NotNull
    private ClientIdInputModel client;

    @Valid
    @NotNull
    private RecipientInputModel recipient;

    @NotNull
    private BigDecimal tax;
}
