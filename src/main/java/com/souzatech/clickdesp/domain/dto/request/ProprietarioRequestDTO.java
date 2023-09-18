package com.souzatech.clickdesp.domain.dto.request;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioRequestDTO {

    private String nome;

    private String cpfOuCnpj;

    private String identidade;

    private String habilitacao;

    private String email;

    private String tipo;

    private String responsavel;

    private Set<String> telefones = new HashSet<>();

}


