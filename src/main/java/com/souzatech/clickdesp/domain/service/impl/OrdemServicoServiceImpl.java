package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.CreateItemOrdemServicoRequest;
import com.souzatech.clickdesp.domain.dto.request.CreateOrdemServicoRequest;
import com.souzatech.clickdesp.domain.dto.response.OrdemServicoResponse;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import com.souzatech.clickdesp.infrastructure.exception.StatusOrdemServicoException;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

    public static final String MSG_ORDEMSERVICO_NAO_ENCONTRADO = "Não existe um cadastro de Ordem de Servico com código %d";
    public static final String MSG_ORDEMSERVICO_EM_USO = "Ordem de Servico de código %d não pode ser removida, pois está em uso";
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

        return modelMapper.map(ordemServico, OrdemServicoResponse.class);
    }

    @Override
    public OrdemServicoResponse update(Long id, CreateOrdemServicoRequest request) {
        var ordemServico = getOrdemServicoId(id);
        ordemServico = getOrdemServico(request);
        ordemServico.setId(id);

        findByIdVeiculo(ordemServico);

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

        ordemServico.setTipoServico(request.getTipoServico());
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

        itemOrdemServicoRepository.saveAll(ordemServico.getItens());

        return ordemServico;
    }

    private void findByIdVeiculo(OrdemServico ordemServico) {
        Long veiculoId = ordemServico.getVeiculo().getId();
        Veiculo veiculo = veiculoService.findById(veiculoId);
        ordemServico.setVeiculo(veiculo);
    }
}
