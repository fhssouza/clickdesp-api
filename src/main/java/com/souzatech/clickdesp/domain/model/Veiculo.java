package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.model.enums.Procedencia;
import com.souzatech.clickdesp.domain.model.enums.TipoCombustivel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Veiculo {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String chassi;

    @Column(nullable = false)
    private String renavam;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCombustivel combustivel;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private Boolean arrendamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Procedencia procedencia;

    @Column(nullable = false)
    private Boolean alienacaoFinduciaria;

    @Column(nullable = false)
    private Integer crv;

    @Column(nullable = false)
    private Date dataCrv;

    @ManyToOne
    private Proprietario proprietario;

    public Veiculo(Long veiculo) {
        id = veiculo;
    }
}
