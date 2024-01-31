package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.TipoServicoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.TipoServicoResponse;
import com.souzatech.clickdesp.domain.model.TipoServico;
import com.souzatech.clickdesp.domain.repository.TipoServicoRepository;
import com.souzatech.clickdesp.domain.service.TipoServicoService;
import com.souzatech.clickdesp.infrastructure.exception.BadRequestException;
import com.souzatech.clickdesp.infrastructure.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoServicoServiceImpl implements TipoServicoService {

    public static final String MSG_ID_NULO = "Id %d da tipoServico deve ser nulo";
    public static final String MSG_TIPOSERVICO_NAO_ENCONTRADO = "Não existe um cadastro de tipoServico com código %d";
    public static final String MSG_TIPOSERVICO_EM_USO = "TipoServico de código %d não pode ser removida, pois está em uso";

    private final TipoServicoRepository repository;

    public TipoServicoServiceImpl(TipoServicoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoServicoResponse> findAll() {
        List<TipoServico> tipoServicos = repository.findAll();
        return tipoServicos.stream()
                .map(c -> modelMapper.map(c, TipoServicoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public TipoServico findById(Long id) {
        return getTipoServico(id);
    }

    @Override
    public TipoServicoResponse create(TipoServicoCreateRequest request) {
        TipoServico tipoServico = new TipoServico();
        tipoServico.setDescricao(request.getDescricao());


        if(Objects.nonNull(tipoServico.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, tipoServico.getId()));
        }
        tipoServico = repository.save(tipoServico);

        return modelMapper.map(tipoServico, TipoServicoResponse.class);
    }

    @Override
    public TipoServicoResponse update(Long id, TipoServicoCreateRequest request) {
        TipoServico tipoServico = getTipoServico(id);
        tipoServico.setDescricao(request.getDescricao());
        tipoServico = repository.save(tipoServico);

        return modelMapper.map(tipoServico, TipoServicoResponse.class);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_TIPOSERVICO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_TIPOSERVICO_EM_USO, id));
        }

    }

    public TipoServico getTipoServico(Long id){
        Optional<TipoServico> tipoServico = repository.findById(id);
        if(tipoServico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_TIPOSERVICO_NAO_ENCONTRADO, id));
        }
        return tipoServico.get();
    }
}
