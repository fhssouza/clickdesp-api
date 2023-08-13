package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico salvar(OrdemServico ordemServico){
        return ordemServicoRepository.save(ordemServico);
    }

    public void excluir(Long ordemServicoId){
        try {
            ordemServicoRepository.deleteById(ordemServicoId);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format("Não existe um cadastro de ordemServico com código %d", ordemServicoId));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format("OrdemServico de código %d não pode ser removida, pois está em uso", ordemServicoId));
        }
    }
}
