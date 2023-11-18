package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "proprietarioNome",
        "cep",
        "logradouro",
        "numero",
        "bairro",
        "complemento",
        "cidadeNome"
})
public class EnderecoResponse implements Serializable {

    @Schema(description="ID gerado pelo banco de dados", example = "1")
    @JsonProperty("Id")
    private Long id;

    @Schema(description="CEP", example = "66110-320")
    @JsonProperty("CEP")
    private String cep;

    @Schema(description="Logradouro", example = "Av. Almirante Barrosso")
    @JsonProperty("Logradouro")
    private String logradouro;

    @Schema(description="Numero", example = "100")
    @JsonProperty("Número")
    private String numero;

    @Schema(description="Complemento", example = "Edificio Alvavile, AP: 1020")
    @JsonProperty("Complemento")
    private String complemento;

    @Schema(description="Bairro", example = "Centro")
    @JsonProperty("Bairro")
    private String bairro;

    @Schema(description="Cidade", example = "1")
    @JsonProperty("Cidade")
    private String cidadeNome;

    @Schema(description="Proprietário", example = "1")
    @JsonProperty("Proprietário")
    private String proprietarioNome;

}
