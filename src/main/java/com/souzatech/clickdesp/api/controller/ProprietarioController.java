package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.ProprietarioDto;
import com.souzatech.clickdesp.domain.mapper.ProprietarioMapper;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
@Api(tags = "Proprietários")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @GetMapping
    @ApiOperation(value = "Listar Proprietários")
    public List<Proprietario> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Proprietários por Id")
    public ResponseEntity<Proprietario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Proprietários")
    public ResponseEntity<ProprietarioDto> create(@RequestBody ProprietarioDto dto, UriComponentsBuilder uriBuilder){
        Proprietario proprietario = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/proprietarios/{id}")
                        .buildAndExpand(proprietario.getId())
                        .toUri())
                .body(ProprietarioMapper.fromEntityDto(proprietario));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Proprietários")
    public ResponseEntity<ProprietarioDto> update(@PathVariable Long id, @RequestBody ProprietarioDto dto, UriComponentsBuilder uriBuilder){
        Proprietario proprietario = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/proprietarios/{id}")
                        .buildAndExpand(proprietario.getId())
                        .toUri())
                .body(ProprietarioMapper.fromEntityDto(proprietario));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Proprietários")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
