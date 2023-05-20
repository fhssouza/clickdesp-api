package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.model.enums.TipoCliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpfOuCnpj;

    @Column(nullable = false)
    private String identidade;

    @Column(nullable = false)
    private String habilitacao;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @Column(nullable = false)
    private String responsavel;

    @Embedded
    private Endereco endereco;
}
