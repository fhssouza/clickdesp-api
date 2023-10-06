package com.souzatech.clickdesp.domain.dto.request;

import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoRequestDTO {

    private StatusOrdemServico status;

    private Date instante;

    private String observacao;

    private Long veiculo;

    private List<ItemOrdemServicoRequestDTO> itens = new ArrayList<>();


}
