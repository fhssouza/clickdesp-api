package com.souzatech.clickdesp.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.souzatech.clickdesp.infrastructure.validation.CnpjGroup;
import com.souzatech.clickdesp.infrastructure.validation.CpfGroup;
import com.souzatech.clickdesp.infrastructure.validation.ProprietarioCreateRequestGroupSequenceProvider;
import com.souzatech.clickdesp.domain.model.enums.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@GroupSequenceProvider(ProprietarioCreateRequestGroupSequenceProvider.class)
public class ProprietarioCreateRequest {

    @JsonProperty("nome")
    @Schema(description="Nome", example = "Fábio Souza")
    private String nome;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    @Enumerated(EnumType.STRING)
    @JsonProperty("tipoPessoa")
    @Schema(description="Tipo Pessoa", example = "FISICA")
    private TipoPessoa tipoPessoa;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @JsonProperty("cpfOuCnpj")
    @Schema(description="CPF ou CNPJ", example = "484.410.340-79")
    private String cpfOuCnpj;

    @JsonProperty("identidade")
    @Schema(description="Identidade", example = "123654")
    private String identidade;

    @JsonProperty("habilitacao")
    @Schema(description="Habilitação", example = "17195859514")
    private String habilitacao;

    @JsonProperty("email")
    @Schema(description="E-Mail", example = "fabio@email.com")
    private String email;

    @JsonProperty("responsavel")
    @Schema(description="Responsável", example = "Fábio Souza")
    private String responsavel;

//    @JsonProperty("telefones")
//    @Schema(description="Telefones", example = "[9999-9999, 8888-8888]")
//    private Set<String> telefones = new HashSet<>();

    @JsonProperty("telefone")
    @Schema(description="Telefone", example = "(99)99999-99999]")
    @Size(min = 15, max = 15, message = "O campo telefone deve possui o tamanho de 15 caracteres" )
    private String telefone;

}


