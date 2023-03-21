package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

// Geram os métodos getters e setters para as propriedades definidas na classe, evitando código boilerplate
@Getter
@Setter
public class Client {
    private Long id;
    private String name;
    private String email;
    private String phone;

}
