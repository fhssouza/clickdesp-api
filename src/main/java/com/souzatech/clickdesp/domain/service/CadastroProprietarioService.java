package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new NotFoundException(
                    String.format("Não existe um cadastro de cliente com código %d", clienteId));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format("Cliente de código %d não pode ser removida, pois está em uso", clienteId));
        }
    }
}
