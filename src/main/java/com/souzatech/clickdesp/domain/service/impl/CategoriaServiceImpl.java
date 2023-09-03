package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.CategoriaRequestDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
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

    public static final String MSG_ID_NULO = "Id %d da categoria deve ser nulo";
    public static final String MSG_CATEGORIA_NAO_ENCONTRADO = "Não existe um cadastro de categoria com código %d";
    public static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removida, pois está em uso";

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
    public Categoria create(CategoriaRequestDto request) {
        Categoria categoria = new Categoria(request);

        if(Objects.nonNull(categoria.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, categoria.getId()));
        }

        return repository.save(categoria);
    }

    @Override
    public Categoria update(Long id, CategoriaRequestDto request) {
        Categoria categoria = new Categoria(request);
        getCategoria(id);
        categoria.setId(id);
        return repository.save(categoria);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_CATEGORIA_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_CATEGORIA_EM_USO, id));
        }

    }

    private Categoria getCategoria(Long id){
        Optional<Categoria> categoria = repository.findById(id);
        if(categoria.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_CATEGORIA_NAO_ENCONTRADO, id));
        }
        return categoria.get();
    }
}
