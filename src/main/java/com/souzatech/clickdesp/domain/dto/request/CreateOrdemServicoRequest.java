package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrdemServicoRequest {

    @JsonProperty("Veículo")
    @Schema(description="Veiculo", example = "1")
    private Long veiculo;

    @JsonProperty("Observações")
    @Schema(description="Observações", example = "Primeiro Emplacamento")
    private String observacao;

    @JsonProperty("Itens")
    private List<CreateItemOrdemServicoRequest> itens = new ArrayList<>();

}
