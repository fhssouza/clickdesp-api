package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.VeiculoRequestDTO;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponseDTO;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
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
    public ResponseEntity<List<VeiculoResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Veículos por Id")
    public ResponseEntity<VeiculoResponseDTO> findById(@PathVariable Long id){
        Veiculo entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(entity, VeiculoResponseDTO.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Veículos")
    public ResponseEntity<VeiculoResponseDTO> create(@RequestBody VeiculoRequestDTO dto, UriComponentsBuilder uriBuilder){
        Veiculo veiculo = service.create(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path("/veiculos/{id}")
                        .buildAndExpand(veiculo.getId())
                        .toUri())
                .body(mapper.map(veiculo, VeiculoResponseDTO.class));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Veículos")
    public ResponseEntity<VeiculoResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody VeiculoRequestDTO dto,
                                                     UriComponentsBuilder uriBuilder){
        Veiculo veiculo = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/veiculos/{id}")
                        .buildAndExpand(veiculo.getId())
                        .toUri())
                .body(mapper.map(veiculo, VeiculoResponseDTO.class));

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Veículos")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
