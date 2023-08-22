package com.souzatech.clickdesp.domain.dto;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class OrdemServicoDto {

    private Long id;

    private StatusOrdemServico status;

    private Date instante;

    private String observacao;

    private Veiculo veiculo;

    private List<ItemOrdemServico> itens = new ArrayList<>();

}
