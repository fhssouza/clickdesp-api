package com.souzatech.clickdesp.domain.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "campo nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo nome deve ter tamanho entre 5 e 30 caracteres" )
    private String nome;

    @NotBlank(message = "campo sobrenome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo sobrenome deve ter tamanho entre 5 e 30 caracteres" )
    private String sobrenome;

    @NotBlank(message = "campo email não pode ser nulo ou vazio")
    @Email(message="Email inválido")
    @Size(min = 5, max = 30, message = "campo email deve ter tamanho entre 5 e 30 caracteres" )
    private String email;

    @NotBlank(message = "campo senha não pode ser nulo ou vazio")
    @Size(min = 6, max = 8, message = "campo senha deve ter tamanho entre 6 e 08 caracteres")
    private String senha;

}
