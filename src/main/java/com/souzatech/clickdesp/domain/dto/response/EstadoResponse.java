package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "nome"
})
public class EstadoResponse {

    @JsonProperty("Id")
    @Schema(description="ID", example = "1")
    private Long id;

    @JsonProperty("Nome")
    @Schema(description="Nome", example = "Pará")
    private String nome;
}
