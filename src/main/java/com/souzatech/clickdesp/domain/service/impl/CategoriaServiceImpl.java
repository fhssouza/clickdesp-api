package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.CategoriaCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.CategoriaResponse;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.repository.CategoriaRepository;
import com.souzatech.clickdesp.domain.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    public static final String MSG_ID_NULO = "Id %d da categoria deve ser nulo";
    public static final String MSG_CATEGORIA_NAO_ENCONTRADO = "Não existe um cadastro de categoria com código %d";
    public static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removida, pois está em uso";

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoriaResponse> findAll() {
        List<Categoria> categorias = repository.findAll();
        return categorias.stream()
                .map(c -> modelMapper.map(c, CategoriaResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponse findById(Long id) {
        Categoria categoria = getCategoria(id);
        return modelMapper.map(categoria, CategoriaResponse.class);
    }

    @Override
    public CategoriaResponse create(CategoriaCreateRequest request) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(request.getDescricao());

        if(Objects.nonNull(categoria.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, categoria.getId()));
        }
        categoria = repository.save(categoria);

        return modelMapper.map(categoria, CategoriaResponse.class);
    }

    @Override
    public CategoriaResponse update(Long id, CategoriaCreateRequest request) {
        Categoria categoria = getCategoria(id);
        categoria.setDescricao(request.getDescricao());
        categoria = repository.save(categoria);

        return modelMapper.map(categoria, CategoriaResponse.class);
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

    public Categoria getCategoria(Long id){
        Optional<Categoria> categoria = repository.findById(id);
        if(categoria.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_CATEGORIA_NAO_ENCONTRADO, id));
        }
        return categoria.get();
    }
}
