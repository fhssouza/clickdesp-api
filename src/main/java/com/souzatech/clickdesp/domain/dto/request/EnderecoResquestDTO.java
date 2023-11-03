package com.souzatech.clickdesp.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResquestDTO implements Serializable {

    private Long proprietario;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private Long cidade;


}
