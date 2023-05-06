package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Delivery {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid // Validar o objeto client (cascata)
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class) // Converte de um grupo de validação para outro, invés de usar o Default usa o outro passado
    @NotNull
    @ManyToOne // Muitas entregas -> Um cliente (padrão do nome da coluna mapeada: client_id. Caso queira usar outro padrão, usar o @JoinColumn)
    private Client client;

    @Valid // Nesse caso, sem conversão (usa o Default group)
    @NotNull
    @Embedded // Ausado para indicar que um objeto deve ser mapeado como um objeto incorporado em uma entidade (fica tudo na tabela de entrega)
    private Recipient recipient;

    @NotNull
    private BigDecimal tax;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Evitar que o cliente preencha esse campo
    @Enumerated(EnumType.STRING) // Significa que queremos armazenar a String que representa a constante da Enum, e não o número (ORDINAL)
    private DeliveryStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime orderDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  LocalDateTime dueDate;
}
