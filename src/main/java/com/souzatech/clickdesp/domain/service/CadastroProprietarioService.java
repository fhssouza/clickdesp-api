package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.exception.EntidadeEmUsoException;
import com.souzatech.clickdesp.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public Proprietario salvar(Proprietario proprietario){
        return proprietarioRepository.save(proprietario);
    }

    public void excluir(Long clienteId){
        try {
            proprietarioRepository.deleteById(clienteId);

        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cliente com código %d", clienteId));

        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Cliente de código %d não pode ser removida, pois está em uso", clienteId));
        }
    }
}
