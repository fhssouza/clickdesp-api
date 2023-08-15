package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.CategoriaDto;
import com.souzatech.clickdesp.domain.mapper.CategoriaMapper;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> created(@RequestBody CategoriaDto dto, UriComponentsBuilder uriBuilder){
        Categoria categoria = service.create(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path("/categorias/{id}")
                        .buildAndExpand(categoria.getId())
                        .toUri())
                .body(CategoriaMapper.fromEntityDto(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Long id, @RequestBody CategoriaDto dto, UriComponentsBuilder uriBuilder){
        Categoria categoria = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/categorias/{id}")
                        .buildAndExpand(categoria.getId())
                        .toUri())
                .body(CategoriaMapper.fromEntityDto(categoria));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
