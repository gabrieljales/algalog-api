package com.algaworks.algalog.commom;

// Como ModelMapper é uma lib independente do Spring, devemos configurar para ele se tornar um Bean gerenciável pelo spring
// Basta somente esse arquivo para isso

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Componente spring com o objetivo de configurar e definir Beans
public class ModelMapperConfig {
    @Bean // Indica que esse método inicializa e configura um Bean que será gerenciado pelo Spring e disponibilizado para injeção em outras classes
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
