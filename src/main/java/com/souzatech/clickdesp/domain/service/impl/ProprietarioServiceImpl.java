package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.ProprietarioDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.ProprietarioMapper;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.ProprietarioRepository;
import com.souzatech.clickdesp.domain.service.CidadeService;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public List<Proprietario> findAll() {
        return repository.findAll();
    }

    @Override
    public Proprietario findById(Long id) {
        return getProprietario(id);
    }

    @Override
    public Proprietario create(ProprietarioDto dto) {
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, dto.getId()));
        }
        return repository.save(ProprietarioMapper.fromDtoEntity(dto));
    }

    @Override
    public Proprietario update(Long id, ProprietarioDto dto) {
        getProprietario(id);
        dto.setId(id);
        return repository.save(ProprietarioMapper.fromDtoEntity(dto));
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

    private Proprietario getProprietario(Long id){
        Optional<Proprietario> servico = repository.findById(id);
        if(servico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_PROPRIETARIO_NAO_ENCONTRADO, id));
        }
        return servico.get();
    }

}
