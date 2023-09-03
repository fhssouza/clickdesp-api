package com.souzatech.clickdesp.domain.mapper;

import com.souzatech.clickdesp.domain.dto.response.EstadoResponse;
import com.souzatech.clickdesp.domain.model.Estado;

public class EstadoMapper {

    public static EstadoResponse fromEntityResponse(Estado entity){
        return  EstadoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
