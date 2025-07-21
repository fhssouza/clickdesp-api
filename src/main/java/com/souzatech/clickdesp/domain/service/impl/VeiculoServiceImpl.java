package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.VeiculoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoPorMesResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoVencimentoResponse;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.model.enums.Procedencia;
import com.souzatech.clickdesp.domain.model.enums.TipoCombustivel;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import com.souzatech.clickdesp.infrastructure.exception.BadRequestException;
import com.souzatech.clickdesp.infrastructure.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    public static final String MSG_ID_NULO = "Id %d do Veiculo deve ser nulo";
    public static final String MSG_VEICULO_NAO_ENCONTRADO = "Não existe um cadastro de Veiculo com código %d";
    public static final String MSG_PLACA_NAO_ENCONTRADO = "Não existe um cadastro de Veiculo com a placa %s";
    public static final String MSG_VEICULO_EM_USO = "Veiculo de código %d não pode ser removida, pois está em uso";
    public static final String MSG_VEICULOS_NAO_ENCONTRADOS_NO_PERIODO = "Nenhum veículo encontrado para o período informado.";

    private final VeiculoRepository repository;

    public VeiculoServiceImpl(VeiculoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VeiculoResponse> findAll() {
        List<Veiculo> entity = repository.findAll();
        return entity.stream()
                .map(v -> modelMapper.map(v, VeiculoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Veiculo findById(Long id) {
        return getVeiculoId(id);
    }

    @Override
    public Veiculo findByPlacaIgnoreCase(String placa) {
        return getVeiculoPlaca(placa);
    }

    @Override
    public VeiculoResponse create(VeiculoCreateRequest request) {

        Veiculo veiculo = new Veiculo();

        if(Objects.nonNull(veiculo.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, veiculo.getId()));
        }

        updateVeiculoFromRequest(veiculo, request);

        findByIdProprietario(veiculo);

        veiculo = repository.save(veiculo);

        return modelMapper.map(veiculo, VeiculoResponse.class);
    }

    @Override
    public VeiculoResponse update(Long id, VeiculoCreateRequest request) {
        Veiculo veiculo = getVeiculoId(id);

        updateVeiculoFromRequest(veiculo, request);

        findByIdProprietario(veiculo);

        veiculo = repository.save(veiculo);

        return modelMapper.map(veiculo, VeiculoResponse.class);
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

    @Override
    public Long countVeiculo() {
        return repository.count();
    }

    @Override
    public List<VeiculoPorMesResponse> countVeiculosPorMes() {
        return repository.countVeiculosPorMes();
    }

    @Override
    public List<VeiculoVencimentoResponse> listarVeiculosParaLicenciamento(LocalDate dataInicio, LocalDate dataFim) {
        List<VeiculoVencimentoResponse> veiculos = repository.listarVeiculosParaLicenciamento(dataInicio, dataFim);
        if(veiculos == null || veiculos.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_VEICULOS_NAO_ENCONTRADOS_NO_PERIODO, dataInicio, dataFim));
        }
        return veiculos;
    }

    private Veiculo getVeiculoId(Long id){
        Optional<Veiculo> veiculo = repository.findById(id);
        if(veiculo.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_VEICULO_NAO_ENCONTRADO, id));
        }
        return veiculo.get();
    }

    private Veiculo getVeiculoPlaca(String placa){
        Optional<Veiculo> veiculo = repository.findByPlacaIgnoreCase(placa);
        if(veiculo.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_PLACA_NAO_ENCONTRADO, placa));
        }
        return veiculo.get();
    }

    private static void updateVeiculoFromRequest(Veiculo entity, VeiculoCreateRequest request) {
        entity.setPlaca(request.getPlaca());
        entity.setMarca(request.getMarca());
        entity.setModelo(request.getModelo());
        entity.setChassi(request.getChassi());
        entity.setRenavam(request.getRenavam());
        entity.setCor(request.getCor());
        entity.setCombustivel(TipoCombustivel.valueOf(request.getCombustivel()));
        entity.setAno(request.getAno());
        entity.setArrendamento(request.getArrendamento());
        entity.setProcedencia(Procedencia.valueOf(request.getProcedencia()));
        entity.setAlienacaoFinduciaria(request.getAlienacaoFinduciaria());
        entity.setCrv(request.getCrv());
        entity.setDataCrv(request.getDataCrv());
        entity.setProprietario(new Proprietario(request.getProprietario()));
    }

    private void findByIdProprietario(Veiculo entity) {
        Long propreitarioId = entity.getProprietario().getId();
        ProprietarioResponse response = proprietarioService.findById(propreitarioId);
        Proprietario proprietario = modelMapper.map(response, Proprietario.class);
        entity.setProprietario(proprietario);
    }
}
