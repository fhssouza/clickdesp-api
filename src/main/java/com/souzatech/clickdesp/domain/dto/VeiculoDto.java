package com.souzatech.clickdesp.domain.dto;

import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.model.enums.Procedencia;
import com.souzatech.clickdesp.domain.model.enums.TipoCombustivel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class VeiculoDto {

    private Long id;

    private String placa;

    private String marca;

    private String modelo;

    private String chassi;

    private String renavam;

    private String cor;

    private TipoCombustivel combustivel;

    private Integer ano;

    private Boolean arrendamento;

    private Procedencia procedencia;

    private Boolean alienacaoFinduciaria;

    private Integer crv;

    private Date dataCrv;

    private Proprietario proprietario;
}
