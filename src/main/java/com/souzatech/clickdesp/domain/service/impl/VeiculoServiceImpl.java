package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.VeiculoDto;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.VeiculoMapper;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    public static final String MSG_ID_NULO = "Id %d do Veiculo deve ser nulo";
    public static final String MSG_VEICULO_NAO_ENCONTRADO = "Não existe um cadastro de Veiculo com código %d";
    public static final String MSG_VEICULO_EM_USO = "Veiculo de código %d não pode ser removida, pois está em uso";

    private final VeiculoRepository repository;

    public VeiculoServiceImpl(VeiculoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ProprietarioService proprietarioService;

    @Override
    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public Veiculo findById(Long id) {
        return getVeiculo(id);
    }

    @Override
    public Veiculo create(VeiculoDto dto) {
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, dto.getId()));
        }
        findByIdProprietario(dto);
        return repository.save(VeiculoMapper.fromDtoEntity(dto));
    }

    @Override
    public Veiculo update(Long id, VeiculoDto dto) {
        getVeiculo(id);
        dto.setId(id);
        findByIdProprietario(dto);
        return repository.save(VeiculoMapper.fromDtoEntity(dto));
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_VEICULO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_VEICULO_EM_USO, id));
        }

    }

    private Veiculo getVeiculo(Long id){
        Optional<Veiculo> veiculo = repository.findById(id);
        if(veiculo.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_VEICULO_NAO_ENCONTRADO, id));
        }
        return veiculo.get();
    }

    private void findByIdProprietario(VeiculoDto dto) {
        Long propreitarioId = dto.getProprietario().getId();
        Proprietario proprietario = proprietarioService.findById(propreitarioId);
        dto.setProprietario(proprietario);
    }
}
