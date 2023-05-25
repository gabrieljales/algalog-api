package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OccurrenceMapper {

    private ModelMapper modelMapper;

    public OccurrenceModel toModel(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceModel.class);
    }

    public List<OccurrenceModel> toCollectionModel(List<Occurrence> occurrences) {
        return occurrences.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
