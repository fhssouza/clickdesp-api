package com.souzatech.clickdesp.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CidadeUpdateDTO {

    private String nome;
    private String estadoId;

}
