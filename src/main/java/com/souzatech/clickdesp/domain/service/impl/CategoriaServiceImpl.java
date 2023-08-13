package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.CategoriaDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.CategoriaMapper;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.repository.CategoriaRepository;
import com.souzatech.clickdesp.domain.service.CategoriaService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return getCategoria(id);
    }

    @Override
    public Categoria create(CategoriaDto dto) {
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestException("Id deve ser nulo");
        }
        return repository.save(CategoriaMapper.fromDtoEntity(dto));
    }

    @Override
    public Categoria update(Long id, CategoriaDto dto) {
        getCategoria(id);
        dto.setId(id);
        return repository.save(CategoriaMapper.fromDtoEntity(dto));
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format("Não existe um cadastro de categoria com código %d", id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format("Categoria de código %d não pode ser removida, pois está em uso", id));
        }

    }

    private Categoria getCategoria(Long id){
        Optional<Categoria> categoria = repository.findById(id);
        if(categoria.isEmpty()){
            throw new NotFoundException(
                    String.format("Não existe um cadastro de categoria com código %d", id));
        }
        return categoria.get();
    }
}