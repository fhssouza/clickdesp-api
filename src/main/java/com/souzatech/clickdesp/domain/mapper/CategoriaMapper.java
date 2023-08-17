package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.CategoriaDto;
import com.souzatech.clickdesp.domain.model.Categoria;

public class CategoriaMapper {

    public static Categoria fromDtoEntity(CategoriaDto dto){
        return Categoria.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .build();
    }

    public static CategoriaDto fromEntityDto(Categoria entity){
        return  CategoriaDto.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .build();
    }
}
