package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroVeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo salvar(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void excluir(Long veiculoId){
        try {
            veiculoRepository.deleteById(veiculoId);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format("Não existe um cadastro de veiculo com código %d", veiculoId));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format("Veiculo de código %d não pode ser removida, pois está em uso", veiculoId));
        }
    }
}
