package com.souzatech.clickdesp.domain.dto;

import com.souzatech.clickdesp.domain.model.Endereco;
import com.souzatech.clickdesp.domain.model.enums.TipoProprietario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProprietarioDto {

    private Long id;

    private String nome;

    private String cpfOuCnpj;

    private String identidade;

    private String habilitacao;

    private String email;

    private TipoProprietario tipo;

    private String responsavel;

    private Set<String> telefones = new HashSet<>();

    private Endereco endereco;

}
