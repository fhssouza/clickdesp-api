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
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@GroupSequenceProvider(ProprietarioCreateRequestGroupSequenceProvider.class)
public class ProprietarioCreateRequest {

    @JsonProperty("Nome")
    @Schema(description="Nome", example = "Fábio Souza")
    private String nome;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    @Enumerated(EnumType.STRING)
    @JsonProperty("Tipo Proprietário")
    @Schema(description="Tipo Pessoa", example = "FISICA")
    private TipoPessoa tipoPessoa;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @JsonProperty("CPF_CNPJ")
    @Schema(description="CPF ou CNPJ", example = "484.410.340-79")
    private String cpfOuCnpj;

    @JsonProperty("Identidade")
    @Schema(description="Identidade", example = "123654")
    private String identidade;

    @JsonProperty("Habilitação")
    @Schema(description="Habilitação", example = "17195859514")
    private String habilitacao;

    @JsonProperty("E-Mail")
    @Schema(description="E-Mail", example = "fabio@email.com")
    private String email;

    @JsonProperty("Responsável")
    @Schema(description="Responsável", example = "Fábio Souza")
    private String responsavel;

    @JsonProperty("Telefones")
    @Schema(description="Telefones", example = "[9999-9999, 8888-8888]")
    private Set<String> telefones = new HashSet<>();

}


