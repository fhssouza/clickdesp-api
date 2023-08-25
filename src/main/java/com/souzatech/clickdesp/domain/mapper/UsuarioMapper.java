package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.UsuarioDTO;
import com.souzatech.clickdesp.domain.model.Usuario;

public class UsuarioMapper {



    public static Usuario fromDtoEntity(UsuarioDTO dto){
        return Usuario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .sobrenome(dto.getSobrenome())
                .email(dto.getEmail())
                .senha((dto.getSenha()))
                .build();
    }

    public static UsuarioDTO fromEntityDto(Usuario entity){
        return  UsuarioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .sobrenome(entity.getSobrenome())
                .email(entity.getEmail())
                .senha((entity.getSenha()))
                .build();
    }
}
