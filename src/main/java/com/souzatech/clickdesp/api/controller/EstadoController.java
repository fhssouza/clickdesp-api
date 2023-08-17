package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.EstadoDto;
import com.souzatech.clickdesp.domain.mapper.EstadoMapper;
import com.souzatech.clickdesp.domain.model.Estado;
import com.souzatech.clickdesp.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoDto> created(@RequestBody EstadoDto dto, UriComponentsBuilder uriBuilder){
        Estado estado = service.create(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path("/estados/{id}")
                        .buildAndExpand(estado.getId())
                        .toUri())
                .body(EstadoMapper.fromEntityDto(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDto> update(@PathVariable Long id, @RequestBody EstadoDto dto, UriComponentsBuilder uriBuilder){
        Estado estado = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/estados/{id}")
                        .buildAndExpand(estado.getId())
                        .toUri())
                .body(EstadoMapper.fromEntityDto(estado));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
