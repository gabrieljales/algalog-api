package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Componente do spring (Tipo onde as instâncias desse tipo são gerenciadas pelo container do spring)
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Método customizado: Atente-se a usar um padrão de nomenclatura com prefixo válido (find..., query... e etc), um delimitador By e o nome da propriedade
    List<Client> findByName(String name);
    List<Client> findByNameContaining(String name); // Nome não exato, usando o LIKE do SQL
    Optional<Client>findByEmail(String email);
}