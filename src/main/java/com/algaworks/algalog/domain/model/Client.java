package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Geram os métodos equals, hashCode, getters e setters para as propriedades definidas na classe, evitando código boilerplate
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Só vai usar propriedades incluídas de forma explícita
@Getter
@Setter
@Entity
public class Client {
    @NotNull(groups = ValidationGroups.ClientId.class) // Especificando grupo de validação
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone;

}
