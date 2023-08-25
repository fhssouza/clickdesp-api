package com.souzatech.clickdesp.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

//    private Perfil perfil;

}
