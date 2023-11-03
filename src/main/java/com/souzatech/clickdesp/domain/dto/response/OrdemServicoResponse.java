package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({
        "id",
        "status",
        "instante",
        "observacao",
        "veiculo",
        "itens"
})
public class OrdemServicoResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Status")
    private StatusOrdemServico status;

    @JsonProperty("Observações")
    private String observacao;

    @JsonProperty("Veiculo")
    private Veiculo veiculo;

    @JsonProperty("Itens")
    private List<ItemOrdemServico> itens = new ArrayList<>();

    public double getValorTotal() {
        double soma = 0.0;
        for(ItemOrdemServico ios : itens){
            soma = soma + ios.getSubtotal();
        }
        return soma;
    }

}
