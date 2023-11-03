package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long veiculo;

    @JsonProperty("Observações")
    private String observacao;

    @JsonProperty("Itens")
    private List<CreateItemOrdemServicoRequest> itens = new ArrayList<>();

}
