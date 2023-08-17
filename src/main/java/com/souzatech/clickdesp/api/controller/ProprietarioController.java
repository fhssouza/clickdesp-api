package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.ProprietarioDto;
import com.souzatech.clickdesp.domain.mapper.ProprietarioMapper;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @GetMapping
    public List<Proprietario> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
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
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
