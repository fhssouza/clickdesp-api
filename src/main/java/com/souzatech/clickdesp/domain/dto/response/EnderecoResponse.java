package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("CEP")
    private String cep;

    @JsonProperty("Logradouro")
    private String logradouro;

    @JsonProperty("Número")
    private String numero;

    @JsonProperty("Complemento")
    private String complemento;

    @JsonProperty("Bairro")
    private String bairro;

    @JsonProperty("Cidade")
    private String cidadeNome;

    @JsonProperty("Proprietário")
    private String proprietarioNome;

}
