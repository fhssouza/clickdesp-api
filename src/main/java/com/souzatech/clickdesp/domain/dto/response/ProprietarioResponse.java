package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.souzatech.clickdesp.domain.model.enums.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
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
        "tipoPessoa",
        "cpfOuCnpj",
        "identidade",
        "habilitacao",
        "email",
        "telfones",
        "responsavel"
})
public class ProprietarioResponse {

    @JsonProperty("Id")
    @Schema(description="ID", example = "1")
    private Long id;

    @JsonProperty("Nome")
    @Schema(description="Nome", example = "Fábio Souza")
    private String nome;

    @JsonProperty("Tipo")
    @Schema(description="Tipo Pessoa", example = "FISICA")
    private TipoPessoa tipoPessoa;

    @JsonProperty("CPF_CNPJ")
    @Schema(description="CPF ou CNPJ", example = "51924278006")
    private String cpfOuCnpj;

    @JsonProperty("Identidade")
    @Schema(description="Identidade", example = "123654")
    private String identidade;

    @JsonProperty("Habilitação")
    @Schema(description="Habilitação", example = "17195859514")
    private String habilitacao;

    @JsonProperty("E-mail")
    @Schema(description="E-Mail", example = "fabio@email.com")
    private String email;

    @JsonProperty("Responsável")
    @Schema(description="Responsável", example = "Fábio Souza")
    private String responsavel;

    @JsonProperty("Telefones")
    @Schema(description="Telefones", example = "[9999-9999, 8888-8888]")
    private Set<String> telefones = new HashSet<>();

}
