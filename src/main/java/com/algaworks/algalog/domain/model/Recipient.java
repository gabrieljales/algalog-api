package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable // Indica ser um objeto incorporado
public class Recipient {
    @Column(name = "recipient_name") // Evitar que o campo "name" referente ao destinatário seja confundido com o nome da entrega
    private String name;

    @Column(name = "recipient_address")
    private String address;

    @Column(name = "recipient_number")
    private  String number;

    @Column(name = "recipient_complement")
    private String complement;

    @Column(name = "recipient_district")
    private String district;
}
