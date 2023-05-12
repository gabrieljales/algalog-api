package com.algaworks.algalog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RecipientInputModel {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private  String number;

    @NotBlank
    private String complement;

    @NotBlank
    private String district;
}
