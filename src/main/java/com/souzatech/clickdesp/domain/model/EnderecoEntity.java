package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.souzatech.clickdesp.domain.dto.EnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnderecoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String logradouro;

    @NotBlank
    private String cep;

    private String complemento;

    private String localidade;

    private String bairro;

    private String uf;

    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proprietario_id", nullable = false)
    @JsonIgnore
    private Proprietario proprietario;

    public EnderecoEntity(EnderecoDto enderecoDto, Proprietario proprietario) {
        this.logradouro = enderecoDto.getLogradouro();
        this.bairro = enderecoDto.getBairro();
        this.cep = enderecoDto.getCep();
        this.complemento = enderecoDto.getComplemento();
        this.localidade = enderecoDto.getLocalidade();
        this.numero = enderecoDto.getNumero();
        this.uf = enderecoDto.getUf();
        this.proprietario = proprietario;
    }
}
