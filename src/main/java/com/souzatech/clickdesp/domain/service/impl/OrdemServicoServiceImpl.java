package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.OrdemServicoDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.OrdemServicoMapper;
import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import com.souzatech.clickdesp.domain.repository.ItemOrdemServicoRepository;
import com.souzatech.clickdesp.domain.repository.OrdemServicoRepository;
import com.souzatech.clickdesp.domain.service.OrdemServicoService;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import com.souzatech.clickdesp.domain.service.ServicoService;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

    public static final String MSG_ID_NULO = "Id %d do Ordem de Servico deve ser nulo";
    public static final String MSG_ORDEMSERVICO_NAO_ENCONTRADO = "Não existe um cadastro de Ordem de Servico com código %d";
    public static final String MSG_ORDEMSERVICO_EM_USO = "Ordem de Servico de código %d não pode ser removida, pois está em uso";

    private final OrdemServicoRepository repository;

    public OrdemServicoServiceImpl(OrdemServicoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ItemOrdemServicoRepository itemOrdemServicoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Override
    public List<OrdemServico> findAll() {
        return repository.findAll();
    }

    @Override
    public OrdemServico findById(Long id) {
        return getOrdemServico(id);
    }

    @Override
    public OrdemServico create(OrdemServico ordemServico) {
        if(Objects.nonNull(ordemServico.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, ordemServico.getId()));
        }
        findByIdVeiculo(ordemServico);
        ordemServico = saveOrdemServico(ordemServico);

        return ordemServico;
    }

    @Override
    public OrdemServico update(Long id, OrdemServicoDto dto) {
        getOrdemServico(id);
        dto.setId(id);
        return repository.save(OrdemServicoMapper.fromDtoEntity(dto));
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_ORDEMSERVICO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_ORDEMSERVICO_EM_USO, id));
        }

    }

    private OrdemServico getOrdemServico(Long id){
        Optional<OrdemServico> ordemServico = repository.findById(id);
        if(ordemServico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_ORDEMSERVICO_NAO_ENCONTRADO, id));
        }
        return ordemServico.get();
    }

    private OrdemServico saveOrdemServico(OrdemServico ordemServico) {
        ordemServico.setInstante(new Date());
        ordemServico.setStatus(StatusOrdemServico.ABERTO);
        ordemServico = repository.save(ordemServico);

        for(ItemOrdemServico ios : ordemServico.getItens()){
            ios.setDesconto(0.0);
            ios.setPreco(servicoService.findById(ios.getServico().getId()).getPreco());
            ios.setServico(servicoService.findById(ios.getServico().getId()));
            ios.setOrdemServico(ordemServico);
        }

        itemOrdemServicoRepository.saveAll(ordemServico.getItens());

        return ordemServico;
    }

    private void findByIdVeiculo(OrdemServico ordemServico) {
        Long veiculoId = ordemServico.getVeiculo().getId();
        Veiculo veiculo = veiculoService.findById(veiculoId);
        ordemServico.setVeiculo(veiculo);
    }
}
