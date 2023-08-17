package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.CidadeDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.CidadeMapper;
import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.model.Estado;
import com.souzatech.clickdesp.domain.repository.CidadeRepository;
import com.souzatech.clickdesp.domain.service.CidadeService;
import com.souzatech.clickdesp.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CidadeServiceImpl implements CidadeService {

    public static final String MSG_ID_NULO = "Id %d da cidade deve ser nulo";
    public static final String MSG_CIDADE_NAO_ENCONTRADO = "Não existe um cadastro de cidade com código %d";
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    private final CidadeRepository repository;

    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private EstadoService service;

    @Override
    public List<Cidade> findAll() {
        return repository.findAll();
    }

    @Override
    public Cidade findById(Long id) {
        return getCidade(id);
    }

    @Override
    public Cidade create(CidadeDto dto) {
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, dto.getId()));
        }
        findByIdEstado(dto);
        return repository.save(CidadeMapper.fromDtoEntity(dto));
    }

    @Override
    public Cidade update(Long id, CidadeDto dto) {
        getCidade(id);
        dto.setId(id);
        findByIdEstado(dto);
        return repository.save(CidadeMapper.fromDtoEntity(dto));
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_CIDADE_EM_USO, id));
        }

    }

    private Cidade getCidade(Long id){
        Optional<Cidade> cidade = repository.findById(id);
        if(cidade.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADO, id));
        }
        return cidade.get();
    }

    private void findByIdEstado(CidadeDto dto) {
        Long estadoId = dto.getEstado().getId();
        Estado estado = service.findById(estadoId);
        dto.setEstado(estado);
    }
}
