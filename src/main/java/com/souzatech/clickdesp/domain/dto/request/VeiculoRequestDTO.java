package com.souzatech.clickdesp.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VeiculoRequestDTO {

    private String placa;

    private String marca;

    private String modelo;

    private String chassi;

    private String renavam;

    private String cor;

    private String combustivel;

    private Integer ano;

    private Boolean arrendamento;

    private String procedencia;

    private Boolean alienacaoFinduciaria;

    private Integer crv;

    private Date dataCrv;

    private Long proprietario;


}
