package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.souzatech.clickdesp.domain.model.EnderecoEntity;
import com.souzatech.clickdesp.domain.model.enums.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "nome",
        "tipoPessoa",
        "cpfOuCnpj",
        "enderecos"
})
public class ProprietarioEnderecoResponse {

    @JsonProperty("Id")
    @Schema(description="ID", example = "1")
    private Long id;

    @JsonProperty("Proprietário")
    @Schema(description="Proprietário", example = "Fábio Souza")
    private String nome;

    @JsonProperty("Tipo Pessoa")
    @Schema(description="Tipo Pessoa", example = "Física")
    private TipoPessoa tipoPessoa;

    @JsonProperty("CPF ou CNPJ")
    @Schema(description="CPF ou CNPJ", example = "39845291040")
    private String cpfOuCnpj;

    @JsonProperty("Endereços")
    @Schema(description="Endereços", example = "endereços")
    private List<EnderecoEntity> enderecos = new ArrayList<>();
}
