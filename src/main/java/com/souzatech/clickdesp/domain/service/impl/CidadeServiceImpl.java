package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.CidadeCreateDTO;
import com.souzatech.clickdesp.domain.dto.request.CidadeUpdateDTO;
import com.souzatech.clickdesp.domain.dto.response.CidadeDTO;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.model.Estado;
import com.souzatech.clickdesp.domain.repository.CidadeRepository;
import com.souzatech.clickdesp.domain.service.CidadeService;
import com.souzatech.clickdesp.domain.service.EstadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CidadeServiceImpl implements CidadeService {

    public static final String MSG_ID_NULO = "Id %d da cidade deve ser nulo";
    public static final String MSG_CIDADE_NAO_ENCONTRADO = "Não existe um cadastro de cidade com código %d";
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    private final CidadeRepository repository;

    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private EstadoService service;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CidadeDTO> findAll() {
        List<Cidade> cidades = repository.findAll();
        return cidades.stream()
                .map(c -> modelMapper.map(c, CidadeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CidadeDTO findById(Long id) {
        Cidade cidade = getCidade(id);
        return  modelMapper.map(cidade, CidadeDTO.class);
    }

    @Override
    public Cidade create(CidadeCreateDTO dto) {
        Cidade entity = new Cidade();
        entity.setNome(dto.getNome());
        entity.setEstado(new Estado(dto.getEstadoId()));

        if(Objects.nonNull(entity.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, entity.getId()));
        }

        findByIdEstado(entity);

        return repository.save(entity);
    }

    @Override
    public Cidade update(Long id, CidadeUpdateDTO dto) {
        Cidade entity = getCidade(id);
        entity.setEstado(new Estado(Long.parseLong(dto.getEstadoId())));
        findByIdEstado(entity);

        return repository.save(modelMapper.map(dto, Cidade.class));
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_CIDADE_EM_USO, id));
        }

    }

    private Cidade getCidade(Long id){
        Optional<Cidade> cidade = repository.findById(id);
        if(cidade.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADO, id));
        }
        return cidade.get();
    }

    private void findByIdEstado(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = service.findById(estadoId);
        cidade.setEstado(estado);
    }
}
