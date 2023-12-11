package com.souzatech.clickdesp.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.souzatech.clickdesp.domain.model.EnderecoEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"classInfo", "fieldInfos", "unknownKeys","factory","empty"})
public class EnderecoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String logradouro;

//    @PeopleCep
    private String cep;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String complemento;

    @JsonProperty(value = "cidade", access = JsonProperty.Access.READ_ONLY)
    private  String localidade;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bairro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uf;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String ddd;

    @NotEmpty
    @Size(min = 1, max = 10)
    private String numero;

    @NotNull
    private Boolean principal;

    public EnderecoDto(EnderecoEntity endereco){
        this(endereco.getId(),
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getLocalidade(),
                endereco.getBairro(),
                endereco.getUf(),
                endereco.getDdd(),
                endereco.getNumero(),
                endereco.getPrincipal());
    }
}
