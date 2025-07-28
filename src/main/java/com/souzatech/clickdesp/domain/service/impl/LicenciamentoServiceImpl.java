package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.LicenciamentoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.LicenciamentoResponse;
import com.souzatech.clickdesp.domain.model.Licenciamento;
import com.souzatech.clickdesp.domain.repository.LicenciamentoRepository;
import com.souzatech.clickdesp.domain.service.LicenciamentoService;
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
public class LicenciamentoServiceImpl implements LicenciamentoService {

    public static final String MSG_ID_NULO = "Id %d da licenciamento deve ser nulo";
    public static final String MSG_LICENCIAMENTOS_NAO_ENCONTRADO_ANO_REFERENCIA = "Licenciamentos não encontrados para o ano ";
    public static final String MSG_LICENCIAMENTO_NAO_ENCONTRADO = "Não existe um cadastro de licenciamento com código %d";
    public static final String MSG_LICENCIAMENTO_EM_USO = "Licenciamento de código %d não pode ser removida, pois está em uso";

    private final LicenciamentoRepository repository;

    public LicenciamentoServiceImpl(LicenciamentoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LicenciamentoResponse> findAll() {
        List<Licenciamento> licenciamentos = repository.findAll();
        return licenciamentos.stream()
                .map(c -> modelMapper.map(c, LicenciamentoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public LicenciamentoResponse findById(Long id) {
        Licenciamento licenciamento = getLicenciamento(id);
        return modelMapper.map(licenciamento, LicenciamentoResponse.class);
    }

    @Override
    public List<LicenciamentoResponse> findByanoReferencia(Integer anoReferencia) {
        List<LicenciamentoResponse> licenciamentos = repository.findByanoReferencia(anoReferencia);

        if (licenciamentos.isEmpty()) {
            throw new NotFoundException(MSG_LICENCIAMENTOS_NAO_ENCONTRADO_ANO_REFERENCIA + anoReferencia);
        }
        return licenciamentos;
    }

    @Override
    public LicenciamentoResponse create(LicenciamentoCreateRequest request) {
        Licenciamento licenciamento = new Licenciamento();
        licenciamento.setFinaPlaca(request.getFinaPlaca());
        licenciamento.setDataVencimento(request.getDataVencimento());
        licenciamento.setAnoReferencia(request.getAnoReferencia());

        if(Objects.nonNull(licenciamento.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, licenciamento.getId()));
        }
        licenciamento = repository.save(licenciamento);

        return modelMapper.map(licenciamento, LicenciamentoResponse.class);
    }

    @Override
    public LicenciamentoResponse update(Long id, LicenciamentoCreateRequest request) {
        Licenciamento licenciamento = getLicenciamento(id);

        licenciamento.setFinaPlaca(request.getFinaPlaca());
        licenciamento.setDataVencimento(request.getDataVencimento());
        licenciamento.setAnoReferencia(request.getAnoReferencia());

        licenciamento = repository.save(licenciamento);

        return modelMapper.map(licenciamento, LicenciamentoResponse.class);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_LICENCIAMENTO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_LICENCIAMENTO_EM_USO, id));
        }

    }

    public Licenciamento getLicenciamento(Long id){
        Optional<Licenciamento> licenciamento = repository.findById(id);
        if(licenciamento.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_LICENCIAMENTO_NAO_ENCONTRADO, id));
        }
        return licenciamento.get();
    }
}
