package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCreateResquest implements Serializable {

    @Schema(description="Proprietário", example = "1")
    @JsonProperty("Proprietário")
    private Long proprietario;

    @Schema(description="CEP", example = "66110-320")
    @JsonProperty("CEP")
    private String cep;

    @Schema(description="Logradouro", example = "Av. Almirante Barrosso")
    @JsonProperty("Logradouro")
    private String logradouro;

    @Schema(description="Numero", example = "100")
    @JsonProperty("Numero")
    private String numero;

    @Schema(description="Complemento", example = "Edificio Alvavile, AP: 1020")
    @JsonProperty("Complemento")
    private String complemento;

    @Schema(description="Bairro", example = "Centro")
    @JsonProperty("Bairro")
    private String bairro;

    @Schema(description="Cidade", example = "1")
    @JsonProperty("Cidade")
    private Long cidade;


}
