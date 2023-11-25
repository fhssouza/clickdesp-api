package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponse;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioCreateRequest;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.ProprietarioRepository;
import com.souzatech.clickdesp.domain.service.CidadeService;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProprietarioServiceImpl implements ProprietarioService {

    public static final String MSG_ID_NULO = "Id %d do proprietario deve ser nulo";
    public static final String MSG_PROPRIETARIO_NAO_ENCONTRADO = "Não existe um cadastro de proprietário com código %d";
    public static final String MSG_PROPRIETARIO_EM_USO = "Proprietário de código %d não pode ser removida, pois está em uso";

    private final ProprietarioRepository repository;

    public ProprietarioServiceImpl(ProprietarioRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private CidadeService service;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProprietarioResponse> findAll() {
        List<Proprietario> entities = repository.findAll();
        return entities.stream()
                .map(p -> modelMapper.map(p, ProprietarioResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Proprietario findById(Long id) {
        return getProprietarioId(id);
    }

    @Override
    public ProprietarioResponse create(ProprietarioCreateRequest request) {
        Proprietario proprietario = getProprietario(request);

        if(Objects.nonNull(proprietario.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, proprietario.getId()));
        }

        proprietario = repository.save(proprietario);

        return modelMapper.map(proprietario, ProprietarioResponse.class);
    }

    @Override
    public ProprietarioResponse update(Long id, ProprietarioCreateRequest request) {
        var proprietario = getProprietarioId(id);
        proprietario = getProprietario(request);
        proprietario.setId(id);

        proprietario = repository.save(proprietario);

        return modelMapper.map(proprietario, ProprietarioResponse.class);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_PROPRIETARIO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_PROPRIETARIO_EM_USO, id));
        }

    }

    private static Proprietario getProprietario(ProprietarioCreateRequest dto) {
        Proprietario entity = new Proprietario();
        entity.setNome(dto.getNome());
        entity.setCpfOuCnpj(dto.getCpfOuCnpj());
        entity.setIdentidade(dto.getIdentidade());
        entity.setHabilitacao(dto.getHabilitacao());
        entity.setEmail(dto.getEmail());
        entity.setTipoPessoa(dto.getTipoPessoa());
        entity.setResponsavel(dto.getResponsavel());
        entity.setTelefones(dto.getTelefones());
        return entity;
    }

    private Proprietario getProprietarioId(Long id){
        Optional<Proprietario> servico = repository.findById(id);
        if(servico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_PROPRIETARIO_NAO_ENCONTRADO, id));
        }
        return servico.get();
    }

}
