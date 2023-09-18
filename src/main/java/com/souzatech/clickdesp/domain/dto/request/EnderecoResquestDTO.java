package com.souzatech.clickdesp.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResquestDTO implements Serializable {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private Long cidade;

    private Long proprietario;
}
