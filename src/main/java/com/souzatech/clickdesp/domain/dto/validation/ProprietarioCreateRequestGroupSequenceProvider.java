package com.souzatech.clickdesp.domain.dto.validation;

import com.souzatech.clickdesp.domain.dto.request.ProprietarioCreateRequest;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioCreateRequestGroupSequenceProvider implements DefaultGroupSequenceProvider<ProprietarioCreateRequest> {
    @Override
    public List<Class<?>> getValidationGroups(ProprietarioCreateRequest request) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(ProprietarioCreateRequest.class);

        if(isPessoaSelecionada(request)){
            groups.add(request.getTipoPessoa().getGroup());
        }
        return groups;
    }

    private boolean isPessoaSelecionada(ProprietarioCreateRequest request) {
        return request != null && request.getTipoPessoa() != null;
    }
}
