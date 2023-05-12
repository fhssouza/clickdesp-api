package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.exception.EntidadeEmUsoException;
import com.souzatech.clickdesp.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void excluir(Long categoriaId){
        try {
            categoriaRepository.deleteById(categoriaId);

        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de categoria com código %d", categoriaId));

        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Categoria de código %d não pode ser removida, pois está em uso", categoriaId));
        }
    }
}
