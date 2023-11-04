package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "nome",
        "estadoNome"
})
public class CidadeResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("Estado")
    private String estadoNome;

}
