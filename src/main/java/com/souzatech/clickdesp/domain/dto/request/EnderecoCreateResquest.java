package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("Proprietário")
    private Long proprietario;

    @JsonProperty("CEP")
    private String cep;

    @JsonProperty("Logradouro")
    private String logradouro;

    @JsonProperty("Numero")
    private String numero;

    @JsonProperty("Complemento")
    private String complemento;

    @JsonProperty("Bairro")
    private String bairro;

    @JsonProperty("Cidade")
    private Long cidade;


}
