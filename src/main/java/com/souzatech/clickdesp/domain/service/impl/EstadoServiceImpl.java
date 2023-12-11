package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.EstadoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.EstadoResponse;
import com.souzatech.clickdesp.infrastructure.exception.BadRequestException;
import com.souzatech.clickdesp.infrastructure.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Estado;
import com.souzatech.clickdesp.domain.repository.EstadoRepository;
import com.souzatech.clickdesp.domain.service.EstadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoServiceImpl implements EstadoService {

    public static final String MSG_ID_NULO = "Id %d da Estado deve ser nulo";
    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de Estado com código %d";
    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

    private final EstadoRepository repository;

    public EstadoServiceImpl(EstadoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EstadoResponse> findAll() {
        List<Estado> estados = repository.findAll();
        return estados.stream()
                .map(estado -> modelMapper.map(estado, EstadoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Estado findById(Long id) {
        return getEstado(id);
    }

    @Override
    public EstadoResponse create(EstadoCreateRequest request) {
        Estado estado = new Estado(request);

        if(Objects.nonNull(estado.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, estado.getId()));
        }

        estado = repository.save(estado);

        return modelMapper.map(estado, EstadoResponse.class);
    }

    @Override
    public EstadoResponse update(Long id, EstadoCreateRequest request) {
        Estado estado = new Estado(request);
        getEstado(id);
        estado.setId(id);
        estado = repository.save(estado);
        return modelMapper.map(estado, EstadoResponse.class);
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
