package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.VeiculoRequestDTO;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponseDTO;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.model.enums.Procedencia;
import com.souzatech.clickdesp.domain.model.enums.TipoCombustivel;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<VeiculoResponseDTO> findAll() {
        List<Veiculo> entity = repository.findAll();
        return entity.stream()
                .map(v -> mapper.map(v, VeiculoResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Veiculo findById(Long id) {
        return getVeiculoId(id);
    }

    @Override
    public Veiculo create(VeiculoRequestDTO dto) {

        Veiculo entity = getVeiculo(dto);

        if(Objects.nonNull(entity.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, entity.getId()));
        }

        findByIdProprietario(entity);

        return repository.save(entity);
    }

    @Override
    public Veiculo update(Long id, VeiculoRequestDTO dto) {
        Veiculo entity = getVeiculoId(id);
        entity = getVeiculo(dto);
        entity.setId(id);
        findByIdProprietario(entity);
        return repository.save(entity);
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

    private Veiculo getVeiculoId(Long id){
        Optional<Veiculo> veiculo = repository.findById(id);
        if(veiculo.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_VEICULO_NAO_ENCONTRADO, id));
        }
        return veiculo.get();
    }

    private static Veiculo getVeiculo(VeiculoRequestDTO dto) {
        Veiculo entity = new Veiculo();
        entity.setPlaca(dto.getPlaca());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setChassi(dto.getChassi());
        entity.setRenavam(dto.getRenavam());
        entity.setCor(dto.getCor());
        entity.setCombustivel(TipoCombustivel.valueOf(dto.getCombustivel()));
        entity.setAno(dto.getAno());
        entity.setArrendamento(dto.getArrendamento());
        entity.setProcedencia(Procedencia.valueOf(dto.getProcedencia()));
        entity.setAlienacaoFinduciaria(dto.getAlienacaoFinduciaria());
        entity.setCrv(dto.getCrv());
        entity.setDataCrv(dto.getDataCrv());
        entity.setProprietario(new Proprietario(dto.getProprietario()));
        return entity;
    }

    private void findByIdProprietario(Veiculo entity) {
        Long propreitarioId = entity.getProprietario().getId();
        Proprietario proprietario = proprietarioService.findById(propreitarioId);
        entity.setProprietario(proprietario);
    }
}
