package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.CreateItemOrdemServicoRequest;
import com.souzatech.clickdesp.domain.dto.request.CreateOrdemServicoRequest;
import com.souzatech.clickdesp.domain.dto.response.OrdemServicoResponse;
import com.souzatech.clickdesp.domain.dto.response.OrdemServicoStatusTotalResponse;
import com.souzatech.clickdesp.domain.model.ItemOrdemServico;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.model.TipoServico;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import com.souzatech.clickdesp.domain.repository.ItemOrdemServicoRepository;
import com.souzatech.clickdesp.domain.repository.OrdemServicoRepository;
import com.souzatech.clickdesp.domain.service.*;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import com.souzatech.clickdesp.infrastructure.exception.StatusOrdemServicoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

    public static final String MSG_ORDEMSERVICO_NAO_ENCONTRADO = "Não existe um cadastro de Ordem de Servico com código %d";
    private static final String MSG_ORDEM_SERVICO_CANCELADA = "Ordem de Servico de código %d não pode ser finalizada, pois já está cancelada";

    private static final String MSG_ORDEM_SERVICO_FINALIZADA = "Ordem de Servico de código %d não pode ser cancelada, pois já está finalizada";

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

    @Autowired
    private TipoServicoService tipoServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrdemServicoResponse> findAll() {
        List<OrdemServico> ordemServicos = repository.findAll();
        return ordemServicos.stream()
                .map(ordemServico -> modelMapper.map(ordemServico, OrdemServicoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrdemServicoResponse findById(Long id) {
        OrdemServico ordemServico = getOrdemServicoId(id);
        return modelMapper.map(ordemServico, OrdemServicoResponse.class);
    }

    @Override
    public OrdemServicoResponse create(CreateOrdemServicoRequest request) {
        OrdemServico ordemServico = getOrdemServico(request);

        findByIdVeiculo(ordemServico);

        ordemServico = repository.save(ordemServico);

        return modelMapper.map(ordemServico, OrdemServicoResponse.class);
    }

    @Override
    public OrdemServicoResponse update(Long id, CreateOrdemServicoRequest request) {
        var ordemServico = getOrdemServicoId(id);

        ordemServico = getUpdateOrdemServico(request, ordemServico);

        return modelMapper.map(ordemServico, OrdemServicoResponse.class);
    }
    @Override
    public OrdemServicoResponse finish(Long id) {
        OrdemServico ordemServico = getOrdemServicoId(id);

        if(StatusOrdemServico.CANCELADA.equals(ordemServico.getStatus())){
            throw new StatusOrdemServicoException(
                    String.format(MSG_ORDEM_SERVICO_CANCELADA, id)
            );
        }

        ordemServico.setStatus(StatusOrdemServico.FINALIZADA);

        repository.save(ordemServico);

        return modelMapper.map(ordemServico, OrdemServicoResponse.class);
    }

    @Override
    public OrdemServicoResponse cancel(Long id) {
        OrdemServico ordemServico = getOrdemServicoId(id);

        if(StatusOrdemServico.FINALIZADA.equals(ordemServico.getStatus())){
            throw new StatusOrdemServicoException(
                    String.format(MSG_ORDEM_SERVICO_FINALIZADA, id)
            );
        }

        ordemServico.setStatus(StatusOrdemServico.CANCELADA);

        repository.save(ordemServico);

        return modelMapper.map(ordemServico, OrdemServicoResponse.class);
    }

    @Override
    public Long countOrdemServico() {
        return repository.count();
    }

    @Override
    public List<OrdemServicoStatusTotalResponse> getTotalOrdemServicoByStatus() {
        return repository.countOrdensByStatus();
    }

    private OrdemServico getOrdemServicoId(Long id){
        Optional<OrdemServico> ordemServico = repository.findById(id);
        if(ordemServico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_ORDEMSERVICO_NAO_ENCONTRADO, id));
        }
        return ordemServico.get();
    }

    private OrdemServico getOrdemServico(CreateOrdemServicoRequest request) {
        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setTipoServico(new TipoServico(request.getTipoServico()));
        ordemServico.setObservacao(request.getObservacao());
        ordemServico.setVeiculo(new Veiculo(request.getVeiculo()));
        ordemServico.setItens(CreateItemOrdemServicoRequest.converter(request.getItens()));

        ordemServico = repository.save(ordemServico);

        for(ItemOrdemServico ios : ordemServico.getItens()){
            ios.setDesconto(0.0);
            ios.setPreco(servicoService.findById(ios.getServico().getId()).getPreco());
            ios.setServico(servicoService.findById(ios.getServico().getId()));
            ios.setOrdemServico(ordemServico);
        }

        findByIdTipoServico(ordemServico);

        findByIdVeiculo(ordemServico);

        itemOrdemServicoRepository.saveAll(ordemServico.getItens());

        return ordemServico;
    }

    private OrdemServico getUpdateOrdemServico(CreateOrdemServicoRequest request, OrdemServico ordemServico) {
        updateOrdemServicoProperties(request, ordemServico);
        updateItens(request, ordemServico);

        findByIdTipoServico(ordemServico);
        findByIdVeiculo(ordemServico);

        itemOrdemServicoRepository.saveAll(ordemServico.getItens());

        return repository.save(ordemServico);
    }

    private void updateOrdemServicoProperties(CreateOrdemServicoRequest request, OrdemServico ordemServico) {
        ordemServico.setTipoServico(new TipoServico(request.getTipoServico()));
        ordemServico.setObservacao(request.getObservacao());
        ordemServico.setVeiculo(new Veiculo(request.getVeiculo()));
    }

    private void updateItens(CreateOrdemServicoRequest request, OrdemServico ordemServico) {
        List<ItemOrdemServico> itensToUpdate = CreateItemOrdemServicoRequest.converter(request.getItens());

        List<ItemOrdemServico> itensToRemove = ordemServico.getItens().stream()
                .filter(existingItem -> itensToUpdate.stream()
                        .noneMatch(item -> Objects.equals(item.getId(), existingItem.getId())))
                .collect(Collectors.toList());

        for (ItemOrdemServico item : itensToRemove) {
            itemOrdemServicoRepository.delete(item);
            ordemServico.getItens().remove(item);
        }

        for (ItemOrdemServico ios : itensToUpdate) {
            ios.setDesconto(0.0);
            ios.setPreco(servicoService.findById(ios.getServico().getId()).getPreco());
            ios.setServico(servicoService.findById(ios.getServico().getId()));
            ios.setOrdemServico(ordemServico);
            ordemServico.getItens().add(ios);
        }
    }
    private void findByIdVeiculo(OrdemServico ordemServico) {
        Long veiculoId = ordemServico.getVeiculo().getId();
        Veiculo veiculo = veiculoService.findById(veiculoId);
        ordemServico.setVeiculo(veiculo);
    }

    private void findByIdTipoServico(OrdemServico ordemServico) {
        Long tipoServicoId = ordemServico.getTipoServico().getId();
        TipoServico tipo = tipoServicoService.findById(tipoServicoId);
        ordemServico.setTipoServico(tipo);
    }

}
