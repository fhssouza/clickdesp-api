package com.souzatech.clickdesp.domain.dto.request;

import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemOrdemServicoRequest {

    @Schema(description="Quantidade", example = "1")
    private Integer quantidade;

    @Schema(description="Serviço", example = "1")
    private Long servico;

    public static List<ItemOrdemServico> converter(List<CreateItemOrdemServicoRequest> itens) {
        return itens.stream().map(ItemOrdemServico::new).collect(Collectors.toList());
    }

}
