package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.ServicoDto;
import com.souzatech.clickdesp.domain.model.Servico;

public class ServicoMapper {

    public static Servico fromDtoEntity(ServicoDto dto){
        return Servico.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoria())
                .build();
    }

    public static ServicoDto fromEntityDto(Servico servico){
        return ServicoDto.builder()
                .id(servico.getId())
                .descricao(servico.getDescricao())
                .categoria(servico.getCategoria())
                .build();
    }
}
