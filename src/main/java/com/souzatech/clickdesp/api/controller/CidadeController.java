package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.CidadeDto;
import com.souzatech.clickdesp.domain.mapper.CidadeMapper;
import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CidadeDto> create(@RequestBody CidadeDto dto, UriComponentsBuilder uriBuilder) {
        Cidade cidade = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                    .path("/cidades/{id}")
                    .buildAndExpand(cidade.getId())
                    .toUri())
                .body(CidadeMapper.fromEntityDto(cidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDto> update(@PathVariable Long id, @RequestBody CidadeDto dto, UriComponentsBuilder uriBuilder) {
        Cidade cidade = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/cidades/{id}")
                        .buildAndExpand(cidade.getId())
                        .toUri())
                .body(CidadeMapper.fromEntityDto(cidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
