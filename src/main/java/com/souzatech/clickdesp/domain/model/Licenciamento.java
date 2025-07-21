package com.souzatech.clickdesp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Licenciamento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String finaPlaca;
    private LocalDate dataVencimento;
    private Integer anoReferencia;
    private Instant createAt;
    private Instant updateAt;

    @OneToMany(mappedBy = "licenciamento")
    private List<Veiculo> veiculos;

    @PrePersist
    public void prePersist(){
        createAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }

}
