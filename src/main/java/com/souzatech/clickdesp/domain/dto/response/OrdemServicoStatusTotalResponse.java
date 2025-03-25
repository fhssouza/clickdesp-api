package com.souzatech.clickdesp.domain.dto.response;

import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoStatusTotalResponse {

    private StatusOrdemServico status;
    private Long total;

}
