package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.model.enums.TipoPessoa;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Builder
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(nullable = false)
    private String cpfOuCnpj;

    @Column(nullable = false)
    private String identidade;

    @Column(nullable = false)
    private String habilitacao;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String responsavel;

//    @ElementCollection
//    @CollectionTable(name = "telefone")
//    private Set<String> telefones = new HashSet<>();

    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Proprietario(Long proprietario) {
        id = proprietario;
    }
}
