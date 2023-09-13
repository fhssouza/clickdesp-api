package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.ServicoCreateDTO;
import com.souzatech.clickdesp.domain.dto.request.ServicoUpdateDTO;
import com.souzatech.clickdesp.domain.dto.response.ServicoResponseDTO;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.repository.ServicoRepository;
import com.souzatech.clickdesp.domain.service.ServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoServiceImpl implements ServicoService {

    public static final String MSG_ID_NULO = "Id %d do serviço deve ser nulo";
    public static final String MSG_SERVICO_NAO_ENCONTRADO = "Não existe um cadastro de serviço com código %d";
    public static final String MSG_SERVICO_EM_USO = "Servico de código %d não pode ser removida, pois está em uso";

    private final ServicoRepository repository;

    public ServicoServiceImpl(ServicoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ServicoResponseDTO> findAll() {
        List<Servico> servicos = repository.findAll();
        return servicos.stream()
                .map(s -> modelMapper.map(s, ServicoResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Servico findById(Long id) {
        return getServico(id);
    }

    @Override
    public Servico create(ServicoCreateDTO dto) {
        Servico entity = new Servico();
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCategoria(new Categoria(dto.getCategoria()));

        if(Objects.nonNull(entity.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, entity.getId()));
        }

        findByIdCategoria(entity);

        return repository.save(entity);
    }

    @Override
    public Servico update(Long id, ServicoUpdateDTO dto) {
        Servico entity = getServico(id);
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCategoria(new Categoria(dto.getCategoria()));

        findByIdCategoria(entity);

        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_SERVICO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_SERVICO_EM_USO, id));
        }

    }

    private Servico getServico(Long id){
        Optional<Servico> servico = repository.findById(id);
        if(servico.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_SERVICO_NAO_ENCONTRADO, id));
        }
        return servico.get();
    }

    private void findByIdCategoria(Servico servico) {
        Long categoriaId = servico.getCategoria().getId();
        Categoria categoria = categoriaService.getCategoria(categoriaId);
        servico.setCategoria(categoria);
    }
}
