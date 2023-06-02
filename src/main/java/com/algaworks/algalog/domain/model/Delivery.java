package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // mappedBy: Nome da propriedade "dona" do relacionamento, cascade: cascateamento para sincronizar as ocorrencias no banco de dados
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    @Enumerated(EnumType.STRING) // Significa que queremos armazenar a String que representa a constante da Enum, e não o número (ORDINAL)
    private DeliveryStatus status;

    private OffsetDateTime orderDate;

    private OffsetDateTime dueDate;

    // Métodos de negócio
    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrence.setDelivery(this); // A própria entrega na qual estamos adicionando a ocorrência

        this.getOccurrences().add(occurrence);
        return occurrence;
    }

    public void finish() {
        if (!deliveryCanBeFinished()) {
            throw new DomainException("Entrega não pode ser finalizada");
        }
        setStatus(DeliveryStatus.COMPLETED);
        setDueDate(OffsetDateTime.now());
    }

    public boolean deliveryCanBeFinished() {
        return DeliveryStatus.PENDING.equals(getStatus());
    }
}
