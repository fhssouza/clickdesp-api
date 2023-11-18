package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "nome",
        "sobrenome",
        "email",
})
public class UsuarioResponse {

    @JsonProperty("Id")
    @Schema(description="ID", example = "1")
    private Long  id;

    @JsonProperty("Nome")
    @Schema(description="Nome", example = "Fábio")
    private String nome;

    @JsonProperty("Sobrenome")
    @Schema(description="Sobrenome", example = "Souza")
    private String sobrenome;

    @JsonProperty("E-mail")
    @Schema(description="E-Mail", example = "souza@email.com")
    private String email;

}
