package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoCreateRequest {

    @JsonProperty("placa")
    @Schema(description="Placa", example = "JUF-0898")
    private String placa;

    @JsonProperty("marca")
    @Schema(description="Marca", example = "Audi")
    private String marca;

    @JsonProperty("modelo")
    @Schema(description="Modelo", example = "RS6 4.2 Avant  BI-TB Quattro Tiptron. 5p")
    private String modelo;

    @JsonProperty("chassi")
    @Schema(description="Chassi", example = "9BD111060T5002156")
    private String chassi;

    @JsonProperty("renavam")
    @Schema(description="Renavam", example = "06127730310")
    private String renavam;

    @JsonProperty("cor")
    @Schema(description="Cor", example = "Laranja")
    private String cor;

    @JsonProperty("combustivel")
    @Schema(description="Cor", example = "FLEX")
    private String combustivel;

    @JsonProperty("ano")
    @Schema(description="Ano", example = "2003")
    private Integer ano;

    @JsonProperty("arrendamento")
    @Schema(description="Arrendamento", example = "SIM")
    private String arrendamento;

    @JsonProperty("procedencia")
    @Schema(description="Arrendamento", example = "IMPORTADO")
    private String procedencia;

    @JsonProperty("alienacaoFinduciaria")
    @Schema(description="Alienação Finduciaria", example = "NÃO")
    private String alienacaoFinduciaria;

    @JsonProperty("crv")
    @Schema(description="CRV", example = "12345")
    private Integer crv;

    @JsonProperty("dataCrv")
    @Schema(description="Data CRV", example = "03/12/2023")
    private String dataCrv;

    @JsonProperty("proprietario")
    @Schema(description="Proprietário", example = "1")
    private Long proprietario;

}
