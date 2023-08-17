package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.EstadoDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.EstadoMapper;
import com.souzatech.clickdesp.domain.model.Estado;
import com.souzatech.clickdesp.domain.repository.EstadoRepository;
import com.souzatech.clickdesp.domain.service.EstadoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstadoServiceImpl implements EstadoService {

    public static final String MSG_ID_NULO = "Id %d da Estado deve ser nulo";
    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de Estado com código %d";
    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

    private final EstadoRepository repository;

    public EstadoServiceImpl(EstadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Estado> findAll() {
        return repository.findAll();
    }

    @Override
    public Estado findById(Long id) {
        return getEstado(id);
    }

    @Override
    public Estado create(EstadoDto dto) {
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, dto.getId()));
        }
        return repository.save(EstadoMapper.fromDtoEntity(dto));
    }

    @Override
    public Estado update(Long id, EstadoDto dto) {
        getEstado(id);
        dto.setId(id);
        return repository.save(EstadoMapper.fromDtoEntity(dto));
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_ESTADO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_ESTADO_EM_USO, id));
        }

    }

    private Estado getEstado(Long id){
        Optional<Estado> estado = repository.findById(id);
        if(estado.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
        }
        return estado.get();
    }
}
