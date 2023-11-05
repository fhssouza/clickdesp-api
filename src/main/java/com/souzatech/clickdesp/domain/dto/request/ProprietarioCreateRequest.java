package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioCreateRequest {

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("CPF_CNPJ")
    private String cpfOuCnpj;

    @JsonProperty("Identidade")
    private String identidade;

    @JsonProperty("Habilitação")
    private String habilitacao;

    @JsonProperty("E-Mail")
    private String email;

    @JsonProperty("Tipo")
    private String tipo;

    @JsonProperty("Responsável")
    private String responsavel;

    @JsonProperty("Telefones")
    private Set<String> telefones = new HashSet<>();

}


