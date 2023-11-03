package com.souzatech.clickdesp.domain.dto.response;

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
public class EnderecoResponseDTO implements Serializable {

    private Long id;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidadeNome;

    private String proprietarioNome;

}
