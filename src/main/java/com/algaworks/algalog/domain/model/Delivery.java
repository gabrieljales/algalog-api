package com.algaworks.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne // Muitas entregas -> Um cliente (padrão do nome da coluna mapeada: client_id. Caso queira usar outro padrão, usar o @JoinColumn)
    private Client client;

    @Embedded // Ausado para indicar que um objeto deve ser mapeado como um objeto incorporado em uma entidade (fica tudo na tabela de entrega)
    private Recipient recipient;

    private BigDecimal tax;

    @Enumerated(EnumType.STRING) // Significa que queremos armazenar a String que representa a constante da Enum, e não o número (ORDINAL)
    private DeliveryStatus status;

    private LocalDateTime orderDate;

    private  LocalDateTime dueDate;
}
