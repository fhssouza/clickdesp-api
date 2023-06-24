package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.exception.EntidadeEmUsoException;
import com.souzatech.clickdesp.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico salvar(Servico servico){
        return servicoRepository.save(servico);
    }

    public void excluir(Long servicoId){
        try {
            servicoRepository.deleteById(servicoId);

        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de servico com código %d", servicoId));

        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Servico de código %d não pode ser removida, pois está em uso", servicoId));
        }
    }
}
