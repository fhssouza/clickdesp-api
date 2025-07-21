package com.souzatech.clickdesp.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenciamentoCreateRequest {

    private String finaPlaca;

    private LocalDate dataVencimento;

    private Integer anoReferencia;

}
