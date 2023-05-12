package com.algaworks.algalog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientIdInputModel {
    @NotNull
    private Long id;
}
