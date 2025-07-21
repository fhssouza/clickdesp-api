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
public class VeiculoVencimentoResponse {

    @Schema(description="Placa", example = "JVC5857")
    private String placa;

    @Schema(description="Modelo", example = "Fit DX 1.4 Flex 16V 5p Aut.")
    private String modelo;

    @Schema(description="Data de Vencimento", example = "31-12-2025")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

}
