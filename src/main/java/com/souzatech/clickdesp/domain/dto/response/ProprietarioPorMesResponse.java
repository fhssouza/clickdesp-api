package com.souzatech.clickdesp.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioPorMesResponse {

    private Integer ano;
    private Integer mes;
    private Long total;

}
