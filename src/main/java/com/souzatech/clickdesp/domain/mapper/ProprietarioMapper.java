package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.ProprietarioDto;
import com.souzatech.clickdesp.domain.model.Proprietario;

public class ProprietarioMapper {

    public static Proprietario fromDtoEntity(ProprietarioDto dto){
        return Proprietario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpfOuCnpj(dto.getCpfOuCnpj())
                .identidade(dto.getIdentidade())
                .habilitacao(dto.getHabilitacao())
                .email(dto.getEmail())
                .tipo(dto.getTipo())
                .responsavel(dto.getResponsavel())
                .telefones(dto.getTelefones())
                .build();
    }

    public static ProprietarioDto fromEntityDto(Proprietario entity){
        return ProprietarioDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpfOuCnpj(entity.getCpfOuCnpj())
                .identidade(entity.getIdentidade())
                .habilitacao(entity.getHabilitacao())
                .email(entity.getEmail())
                .tipo(entity.getTipo())
                .responsavel(entity.getResponsavel())
                .telefones(entity.getTelefones())
                .build();
    }

}
