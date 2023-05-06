package com.algaworks.algalog.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Só incluir propriedades que não estejam nulas (evitar de colocar campos nulos)
@Getter
@Setter
public class StandardError {
    private Integer status;
    private OffsetDateTime dateTime;
    private String message;
    private List<Field> fields;

    @AllArgsConstructor
    @Getter
    public static class Field {
        private String name;
        private String message;
    }
}
