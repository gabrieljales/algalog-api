package com.algaworks.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Geram os métodos equals, hashCode, getters e setters para as propriedades definidas na classe, evitando código boilerplate
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Só vai usar propriedades incluídas de forma explícita
@Getter
@Setter
@Entity
public class Client {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

}
