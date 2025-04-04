package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.ConsultarEndereco;
import com.souzatech.clickdesp.domain.dto.request.EnderecoRequest;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.ProprietarioEnderecoResponse;
import com.souzatech.clickdesp.domain.dto.response.ProprietarioPorMesResponse;
import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponse;
import com.souzatech.clickdesp.domain.model.Endereco;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.EnderecoRepository;
import com.souzatech.clickdesp.domain.repository.ProprietarioRepository;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import com.souzatech.clickdesp.infrastructure.config.ConsumerAPIViaCepConfiguration;
import com.souzatech.clickdesp.infrastructure.exception.BadRequestException;
import com.souzatech.clickdesp.infrastructure.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProprietarioServiceImpl implements ProprietarioService {

    public static final String MSG_ID_NULO = "Id %d do proprietario deve ser nulo";
    public static final String MSG_PROPRIETARIO_NAO_ENCONTRADO = "Não existe um cadastro de proprietário com código %d";
    public static final String MSG_PROPRIETARIO_EM_USO = "Proprietário de código %d não pode ser removida, pois está em uso";

    private final ProprietarioRepository repository;

    public ProprietarioServiceImpl(ProprietarioRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConsumerAPIViaCepConfiguration apiViaCepConfiguration;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public List<ProprietarioResponse> findAll() {
        List<Proprietario> entities = repository.findAll();
        return entities.stream()
                .map(p -> modelMapper.map(p, ProprietarioResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProprietarioResponse findById(Long id) {
        var proprietario = getProprietarioId(id);
        return modelMapper.map(proprietario, ProprietarioResponse.class);
    }

    @Override
    public ProprietarioResponse create(ProprietarioCreateRequest request) {

        Proprietario proprietario = new Proprietario();

        if(Objects.nonNull(proprietario.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, proprietario.getId()));
        }

        updateProprietarioFromRequest(proprietario, request);

        proprietario = repository.save(proprietario);

        return modelMapper.map(proprietario, ProprietarioResponse.class);
    }

    @Override
    public ProprietarioResponse update(Long id, ProprietarioCreateRequest request) {
        Proprietario proprietario = getProprietarioId(id);

        updateProprietarioFromRequest(proprietario, request);

        proprietario = repository.save(proprietario);

        return modelMapper.map(proprietario, ProprietarioResponse.class);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_PROPRIETARIO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_PROPRIETARIO_EM_USO, id));
        }

    }

    @Override
    public ProprietarioEnderecoResponse createEndereco(Long proprietarioId, EnderecoRequest request) {

        var proprietario = getProprietarioId(proprietarioId);

        Endereco novoEndereco = buscarEndereco(request);

        novoEndereco.setProprietario(proprietario);
        novoEndereco.setCep(request.getCep());
        novoEndereco.setComplemento(request.getComplemento());
        novoEndereco.setLocalidade(request.getLocalidade());
        novoEndereco.setBairro(request.getBairro());
        novoEndereco.setUf(request.getUf());
        novoEndereco.setNumero(request.getNumero());

        proprietario.getEnderecos().add(novoEndereco);

        enderecoRepository.saveAll(proprietario.getEnderecos());

        return modelMapper.map(proprietario, ProprietarioEnderecoResponse.class);
    }

    @Override
    public ProprietarioEnderecoResponse findByIdProprietarioEndereco(Long id) {
        var proprietario = getProprietarioId(id);
        return modelMapper.map(proprietario, ProprietarioEnderecoResponse.class);
    }

    @Override
    public Long countProprietario() {
        return repository.count();
    }

    @Override
    public List<ProprietarioPorMesResponse> countProprietariosPorMes() {
        return repository.countProprietariosPorMes();
    }

    private void updateProprietarioFromRequest(Proprietario entity, ProprietarioCreateRequest dto) {
        entity.setNome(dto.getNome());
        entity.setCpfOuCnpj(dto.getCpfOuCnpj());
        entity.setIdentidade(dto.getIdentidade());
        entity.setHabilitacao(dto.getHabilitacao());
        entity.setEmail(dto.getEmail());
        entity.setTipoPessoa(dto.getTipoPessoa());
        entity.setResponsavel(dto.getResponsavel());
        entity.setTelefone(dto.getTelefone());
    }

    private Proprietario getProprietarioId(Long id){
        Optional<Proprietario> proprietario = repository.findById(id);
        if(proprietario.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_PROPRIETARIO_NAO_ENCONTRADO, id));
        }
        return proprietario.get();
    }

    private Endereco buscarEndereco(EnderecoRequest request) {

        ConsultarEndereco consultarEndereco = apiViaCepConfiguration.consumer(request.getCep());

        request.setCep(consultarEndereco.getCep());
        request.setLogradouro(consultarEndereco.getLogradouro());
        request.setLocalidade(consultarEndereco.getLocalidade());
        request.setBairro(consultarEndereco.getBairro());
        request.setUf(consultarEndereco.getUf());

        return modelMapper.map(request, Endereco.class);

    }

}
