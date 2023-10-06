package com.souzatech.clickdesp.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrdemServicoRequestDTO {

    private Integer quantidade;
    private Long servico;

}
