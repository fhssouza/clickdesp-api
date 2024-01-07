package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import io.swagger.v3.oas.annotations.media.Schema;
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
        "veiculoPlaca",
        "proprietarioNome",
        "itens"
})
public class OrdemServicoResponse {

    @JsonProperty("id")
    @Schema(description="ID", example = "1")
    private Long id;

    @JsonProperty("status")
    @Schema(description="Status", example = "ABERTO")
    private StatusOrdemServico status;

    @JsonProperty("observacao")
    @Schema(description="Observações", example = "Primeiro Emplacamento")
    private String observacao;

    @JsonProperty("veiculoPlaca")
    @Schema(description="Placa", example = "JVX-3030")
    private String veiculoPlaca;

    @JsonProperty("proprietarioNome")
    private String veiculoProprietarioNome;

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
