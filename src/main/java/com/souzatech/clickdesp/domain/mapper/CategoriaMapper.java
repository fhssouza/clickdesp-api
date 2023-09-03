package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.request.CategoriaRequestDto;
import com.souzatech.clickdesp.domain.dto.response.CategoriaResponseDto;
import com.souzatech.clickdesp.domain.model.Categoria;

public class CategoriaMapper {

    public static Categoria fromDtoEntityRequest(CategoriaRequestDto req){
        return Categoria.builder()
                .descricao(req.getDescricao())
                .build();
    }

    public static CategoriaResponseDto fromEntityResponse(Categoria entity){
        return  CategoriaResponseDto.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .build();
    }
}
