package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoParaLicenciamentoResponse {

    @Schema(description="Placa", example = "JVC5801")
    private String placa;

    @Schema(description="Modelo", example = "Fit DX 1.4 Flex 16V 5p Aut.")
    private String modelo;

    @Schema(description="Marca", example = "Honda")
    private String marca;

    @Schema(description="Nome do Proprietário", example = "João da Silva")
    private String nomeProprietario;

    @Schema(description="Data de Vencimento do Licenciamento", example = "30/04/2024")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
}
