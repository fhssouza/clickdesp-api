package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.EstadoDto;
import com.souzatech.clickdesp.domain.model.Estado;

public class EstadoMapper {

    public static Estado fromDtoEntity(EstadoDto dto){
        return Estado.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }

    public static EstadoDto fromEntityDto(Estado entity){
        return  EstadoDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
