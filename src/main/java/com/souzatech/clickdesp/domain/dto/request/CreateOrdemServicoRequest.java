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

    @JsonProperty("veiculo")
    @Schema(description="Veiculo", example = "1")
    private Long veiculo;

    @JsonProperty("tipoServico")
    @Schema(description="tipoServico", example = "1")
    private Long tipoServico;

    @JsonProperty("observacao")
    @Schema(description="Observações", example = "Vistoria Pendente")
    private String observacao;

    @JsonProperty("itens")
    private List<CreateItemOrdemServicoRequest> itens = new ArrayList<>();

}
