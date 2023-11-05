package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.souzatech.clickdesp.domain.model.enums.TipoProprietario;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "nome",
        "tipo",
        "cpfOuCnpj",
        "identidade",
        "habilitacao",
        "email",
        "telfones",
        "responsavel"

})
public class ProprietarioResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("CPF_CNPJ")
    private String cpfOuCnpj;

    @JsonProperty("Identidade")
    private String identidade;

    @JsonProperty("Habilitação")
    private String habilitacao;

    @JsonProperty("E-mail")
    private String email;

    @JsonProperty("Tipo")
    private TipoProprietario tipo;

    @JsonProperty("Responsável")
    private String responsavel;

    @JsonProperty("Telefones")
    private Set<String> telefones = new HashSet<>();

}
