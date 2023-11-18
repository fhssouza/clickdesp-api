package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description="ID", example = "1")
    private Long id;

    @JsonProperty("Placa")
    @Schema(description="Placa", example = "JUF-0898")
    private String placa;

    @JsonProperty("Marca")
    @Schema(description="Marca", example = "Audi")
    private String marca;

    @JsonProperty("Modelo")
    @Schema(description="Modelo", example = "RS6 4.2 Avant  BI-TB Quattro Tiptron. 5p")
    private String modelo;

    @JsonProperty("Chassi")
    @Schema(description="Chassi", example = "9BD111060T5002156")
    private String chassi;

    @JsonProperty("Renavam")
    @Schema(description="Renavam", example = "06127730310")
    private String renavam;

    @JsonProperty("Cor")
    @Schema(description="Cor", example = "Laranja")
    private String cor;

    @JsonProperty("Combustível")
    @Schema(description="Cor", example = "FLEX")
    private String combustivel;

    @JsonProperty("Ano")
    @Schema(description="Ano", example = "2003")
    private Integer ano;

    @JsonProperty("Arrendamento")
    @Schema(description="Arrendamento", example = "FALSE")
    private Boolean arrendamento;

    @JsonProperty("Procedencia")
    @Schema(description="Arrendamento", example = "IMPORTADO")
    private String procedencia;

    @JsonProperty("Alienação Finduciaria")
    @Schema(description="Alienação Finduciaria", example = "FALSE")
    private Boolean alienacaoFinduciaria;

    @JsonProperty("CRV")
    @Schema(description="CRV", example = "81065431584")
    private Integer crv;

    @JsonProperty("Data CRV")
    @Schema(description="Data CRV", example = "2011-12-03")
    private Date dataCrv;

    @JsonProperty("Proprietário")
    @Schema(description="Proprietário", example = "1")
    private String proprietarioNome;

}
