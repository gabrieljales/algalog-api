package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable // Indica ser um objeto incorporado
public class Recipient {
    @NotBlank
    @Column(name = "recipient_name") // Evitar que o campo "name" referente ao destinatário seja confundido com o nome da entrega
    private String name;

    @NotBlank
    @Column(name = "recipient_address")
    private String address;

    @NotBlank
    @Column(name = "recipient_number")
    private  String number;

    @NotBlank
    @Column(name = "recipient_complement")
    private String complement;

    @NotBlank
    @Column(name = "recipient_district")
    private String district;
}
