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
public class UsuarioEmailRequest {

    @NotBlank(message = "campo email não pode ser nulo ou vazio")
    @Email(message="Email inválido")
    @Size(min = 5, max = 30, message = "campo email deve ter tamanho entre 5 e 30 caracteres" )
    @JsonProperty("email")
    @Schema(description="E-Mail", example = "souza@email.com")
    private String email;

}
