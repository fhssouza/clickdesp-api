package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.model.enums.Procedencia;
import com.souzatech.clickdesp.domain.model.enums.TipoCombustivel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veiculo {

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
    private String arrendamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Procedencia procedencia;

    @Column(nullable = false)
    private String alienacaoFinduciaria;

    @Column(nullable = false)
    private String crv;

    @Column(nullable = false)
    private String dataCrv;

    private Instant createAt;

    private Instant updateAt;

    @ManyToOne
    private Proprietario proprietario;

    public Veiculo(Long veiculo) {
        id = veiculo;
    }

    @PrePersist
    public void prePersist(){
        createAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }
}
