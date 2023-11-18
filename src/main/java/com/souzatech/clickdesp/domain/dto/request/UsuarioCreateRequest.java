package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class UsuarioCreateRequest {

    @NotBlank(message = "campo nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo nome deve ter tamanho entre 5 e 30 caracteres" )
    @JsonProperty("Nome")
    @Schema(description="Nome", example = "Fábio")
    private String nome;

    @NotBlank(message = "campo sobrenome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo sobrenome deve ter tamanho entre 5 e 30 caracteres" )
    @JsonProperty("Sobrenome")
    @Schema(description="Sobrenome", example = "Souza")
    private String sobrenome;

    @NotBlank(message = "campo email não pode ser nulo ou vazio")
    @Email(message="Email inválido")
    @Size(min = 5, max = 30, message = "campo email deve ter tamanho entre 5 e 30 caracteres" )
    @JsonProperty("E-mail")
    @Schema(description="E-Mail", example = "souza@email.com")
    private String email;

    @NotBlank(message = "campo senha não pode ser nulo ou vazio")
    @Size(min = 6, max = 8, message = "campo senha deve ter tamanho entre 6 e 08 caracteres")
    @JsonProperty("Senha")
    @Schema(description="Senha", example = "fa@123")
    private String senha;

}
