package com.souzatech.clickdesp.domain.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConsultarEndereco {

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private  String uf;

    private String ibge;

    private String gia;

    private  String ddd;

    private String siafi;
}
