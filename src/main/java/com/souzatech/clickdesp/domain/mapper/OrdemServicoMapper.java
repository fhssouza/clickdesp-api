package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.OrdemServicoDto;
import com.souzatech.clickdesp.domain.model.OrdemServico;

public class OrdemServicoMapper {

    public static OrdemServico fromDtoEntity(OrdemServicoDto dto){
        return OrdemServico.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .dataCriacao(dto.getDataCriacao())
                .observacao(dto.getObservacao())
                .veiculo(dto.getVeiculo())
                .itens(dto.getItens())
                .build();
    }

    public static OrdemServicoDto fromEntityDto(OrdemServico entity){
        return  OrdemServicoDto.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .dataCriacao(entity.getDataCriacao())
                .observacao(entity.getObservacao())
                .veiculo(entity.getVeiculo())
                .itens(entity.getItens())
                .build();
    }
}

