package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.CidadeDto;
import com.souzatech.clickdesp.domain.dto.ServicoDto;
import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.model.Servico;

public class CidadeMapper {

    public static Cidade fromDtoEntity(CidadeDto dto){
        return Cidade.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .estado(dto.getEstado())
                .build();
    }

    public static CidadeDto fromEntityDto(Cidade entity){
        return CidadeDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .estado(entity.getEstado())
                .build();
    }
}
