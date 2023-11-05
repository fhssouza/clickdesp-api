package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.EnderecoCreateResquest;
import com.souzatech.clickdesp.domain.dto.response.EnderecoResponse;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.model.Endereco;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.EnderecoRepository;
import com.souzatech.clickdesp.domain.service.EnderecoService;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    public static final String MSG_ID_NULO = "Id %d do endereço deve ser nulo";
    public static final String MSG_ENDERECO_NAO_ENCONTRADO = "Não existe um cadastro de endereço com código %d";
    public static final String MSG_ENDERECO_EM_USO = "Endereco de código %d não pode ser removida, pois está em uso";

    private final EnderecoRepository repository;

    public EnderecoServiceImpl(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EnderecoResponse> findAll() {
        List<Endereco> servicos = repository.findAll();
        return servicos.stream()
                .map(s -> modelMapper.map(s, EnderecoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Endereco findById(Long id) {
        return getEnderecoId(id);
    }

    @Override
    public EnderecoResponse create(EnderecoCreateResquest request) {
        Endereco endereco = getEndereco(request);

        if(Objects.nonNull(endereco.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, endereco.getId()));
        }

        findByIdProprietario(endereco);

        endereco = repository.save(endereco);

        return modelMapper.map(endereco, EnderecoResponse.class);
    }



    @Override
    public EnderecoResponse update(Long id, EnderecoCreateResquest request) {
        var endereco = getEnderecoId(id);

        endereco = getEndereco(request);
        endereco.setId(id);

        findByIdProprietario(endereco);

        endereco = repository.save(endereco);

        return modelMapper.map(endereco, EnderecoResponse.class);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_ENDERECO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_ENDERECO_EM_USO, id));
        }

    }

    private static Endereco getEndereco(EnderecoCreateResquest dto) {
        Endereco entity = new Endereco();
        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCidade(new Cidade(dto.getCidade()));
        entity.setProprietario(new Proprietario(dto.getProprietario()));
        return entity;
    }
    private Endereco getEnderecoId(Long id){
        Optional<Endereco> servico = repository.findById(id);
        if(servico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_ENDERECO_NAO_ENCONTRADO, id));
        }
        return servico.get();
    }

    private void findByIdProprietario(Endereco entity) {
        Long propreitarioId = entity.getProprietario().getId();
        Proprietario proprietario = proprietarioService.findById(propreitarioId);
        entity.setProprietario(proprietario);
    }
}
