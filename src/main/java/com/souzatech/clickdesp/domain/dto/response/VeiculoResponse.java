package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "placa",
        "marca",
        "modelo",
        "chassis",
        "renavam",
        "cor",
        "combustivel",
        "ano",
        "arrendamento",
        "procedentcia",
        "alienacaoFinduciaria",
        "crv",
        "dataCrv",
        "proprietarioNome"
})
public class VeiculoResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Placa")
    private String placa;

    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("Chassi")
    private String chassi;

    @JsonProperty("Renavam")
    private String renavam;

    @JsonProperty("Cor")
    private String cor;

    @JsonProperty("Combustível")
    private String combustivel;

    @JsonProperty("Ano")
    private Integer ano;

    @JsonProperty("Arrendamento")
    private Boolean arrendamento;

    @JsonProperty("Procedencia")
    private String procedencia;

    @JsonProperty("Alienação Finduciaria")
    private Boolean alienacaoFinduciaria;

    @JsonProperty("CRV")
    private Integer crv;

    @JsonProperty("Data CRV")
    private Date dataCrv;

    @JsonProperty("Proprietário")
    private String proprietarioNome;


}
