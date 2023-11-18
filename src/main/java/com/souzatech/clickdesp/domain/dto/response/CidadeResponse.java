package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "nome",
        "estadoNome"
})
public class CidadeResponse {

    @JsonProperty("Id")
    @Schema(description="ID gerado pelo banco de dados", example = "1")
    private Long id;

    @JsonProperty("Nome")
    @Schema(description="Descrição da Cidade", example = "Belém")
    private String nome;

    @JsonProperty("Estado")
    @Schema(description="Descrição da Estado", example = "Pará")
    private String estadoNome;

}
