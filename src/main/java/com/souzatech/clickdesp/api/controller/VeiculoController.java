package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.VeiculoDto;
import com.souzatech.clickdesp.domain.mapper.VeiculoMapper;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping
    @ApiOperation(value = "Listar Veículos")
    public ResponseEntity<List<Veiculo>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Veículos por Id")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Veículos")
    public ResponseEntity<VeiculoDto> create(@RequestBody VeiculoDto dto, UriComponentsBuilder uriBuilder){
        Veiculo veiculo = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/veiculos/{id}")
                        .buildAndExpand(veiculo.getId())
                        .toUri())
                .body(VeiculoMapper.fromEntityDto(veiculo));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Veículos")
    public ResponseEntity<VeiculoDto> update(@PathVariable Long id, @RequestBody VeiculoDto dto, UriComponentsBuilder uriBuilder){
        Veiculo veiculo = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/veiculos/{id}")
                        .buildAndExpand(veiculo.getId())
                        .toUri())
                .body(VeiculoMapper.fromEntityDto(veiculo));

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Veículos")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
