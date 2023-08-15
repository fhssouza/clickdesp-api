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

    public static CategoriaDto fromEntityDto(Categoria categoria){
        return  CategoriaDto.builder()
                .id(categoria.getId())
                .descricao(categoria.getDescricao())
                .build();
    }
}
