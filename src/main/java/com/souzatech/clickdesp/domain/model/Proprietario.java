package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.model.enums.TipoProprietario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Proprietario {

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
    private TipoProprietario tipo;

    @Column(nullable = false)
    private String responsavel;

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    @Embedded
    private Endereco endereco;

}
