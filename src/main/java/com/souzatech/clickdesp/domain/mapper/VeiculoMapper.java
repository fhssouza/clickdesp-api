package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.VeiculoDto;
import com.souzatech.clickdesp.domain.model.Veiculo;

public class VeiculoMapper {

    public static Veiculo fromDtoEntity(VeiculoDto dto){
        return Veiculo.builder()
                .id(dto.getId())
                .placa(dto.getPlaca())
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .chassi(dto.getChassi())
                .renavam(dto.getRenavam())
                .cor(dto.getCor())
                .combustivel(dto.getCombustivel())
                .ano(dto.getAno())
                .arrendamento(dto.getArrendamento())
                .procedencia(dto.getProcedencia())
                .alienacaoFinduciaria(dto.getAlienacaoFinduciaria())
                .crv(dto.getCrv())
                .dataCrv(dto.getDataCrv())
                .proprietario(dto.getProprietario())
                .build();
    }

    public static VeiculoDto fromEntityDto(Veiculo entity){
        return  VeiculoDto.builder()
                .id(entity.getId())
                .placa(entity.getPlaca())
                .marca(entity.getMarca())
                .modelo(entity.getModelo())
                .chassi(entity.getChassi())
                .renavam(entity.getRenavam())
                .cor(entity.getCor())
                .combustivel(entity.getCombustivel())
                .ano(entity.getAno())
                .arrendamento(entity.getArrendamento())
                .procedencia(entity.getProcedencia())
                .alienacaoFinduciaria(entity.getAlienacaoFinduciaria())
                .crv(entity.getCrv())
                .dataCrv(entity.getDataCrv())
                .proprietario(entity.getProprietario())
                .build();
    }
}
