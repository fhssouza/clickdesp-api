package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.VeiculoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.VeiculoParaLicenciamentoResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoVencimentoResponse;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Veículos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @ApiOperation(value = "Listar Veículos")
    public ResponseEntity<List<VeiculoResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Veículos por Id")
    public ResponseEntity<VeiculoResponse> findById(@PathVariable Long id){
        Veiculo entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(entity, VeiculoResponse.class));
    }

    @GetMapping("/placa")
    @ApiOperation(value = "Listar Veículo por Placa")
    public ResponseEntity<VeiculoResponse> findByPlaca(String placa){
        Veiculo entity = service.findByPlacaIgnoreCase(placa);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(entity, VeiculoResponse.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Veículos")
    public ResponseEntity<VeiculoResponse> create(@RequestBody VeiculoCreateRequest request, UriComponentsBuilder uriBuilder){
        VeiculoResponse response = service.create(request);
        return ResponseEntity
                .created(uriBuilder
                        .path("/veiculos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Veículos")
    public ResponseEntity<VeiculoResponse> update(@PathVariable Long id,
                                                  @RequestBody VeiculoCreateRequest request,
                                                  UriComponentsBuilder uriBuilder){
        VeiculoResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/veiculos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Veículos")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/vencimentos")
    public ResponseEntity<List<VeiculoVencimentoResponse>> listarVeiculosParaLicenciamento(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {
        List<VeiculoVencimentoResponse> veiculosComVencimento = service.listarVeiculosParaLicenciamento(dataInicio, dataFim);
        return ResponseEntity.ok(veiculosComVencimento);
    }

    @GetMapping("/licenciamento/final-placa/{finalPlaca}")
    @ApiOperation(value = "Listar veículos para licenciamento por final da placa e por proprietário")
    public ResponseEntity<List<VeiculoParaLicenciamentoResponse>> findByFinalPlacaAndProprietario(
            @PathVariable String finalPlaca,
            @RequestParam Long proprietarioId) {
        List<VeiculoParaLicenciamentoResponse> veiculos = service.findByFinalPlacaAndProprietario(finalPlaca, proprietarioId);
        return ResponseEntity.ok(veiculos);
    }
}
