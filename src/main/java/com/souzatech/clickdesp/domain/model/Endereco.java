package com.souzatech.clickdesp.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero",nullable = false)
    private String numero;

    @Column(name = "endereco_complemento",nullable = false)
    private String complemento;

    @Column(name = "endereco_bairro",nullable = false)
    private String bairro;

    @Column(name="endereco_cep", nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;
}
