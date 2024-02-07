package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.souzatech.clickdesp.domain.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"classInfo", "fieldInfos", "unknownKeys","factory","empty"})
public class EnderecoRequest {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String logradouro;

//    @PeopleCep
    @JsonProperty("cep")
    @Schema(description="CEP", example = "66110350")
    private String cep;

    @JsonProperty("complemento")
    @Schema(description="Complemento", example = "Edificio Souza, APT 500")
    private String complemento;

    @JsonProperty(value = "cidade", access = JsonProperty.Access.READ_ONLY)
    private  String localidade;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bairro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uf;

    @NotEmpty
    @Size(min = 1, max = 10)
    @JsonProperty("numero")
    @Schema(description="Número", example = "90")
    private String numero;

    public EnderecoRequest(Endereco endereco){
        this(endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getLocalidade(),
                endereco.getBairro(),
                endereco.getUf(),
                endereco.getNumero());
    }

}
