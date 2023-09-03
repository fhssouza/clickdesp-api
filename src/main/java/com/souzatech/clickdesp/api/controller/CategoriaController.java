package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.CategoriaRequestDto;
import com.souzatech.clickdesp.domain.dto.response.CategoriaResponseDto;
import com.souzatech.clickdesp.domain.mapper.CategoriaMapper;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@Api(tags = "Categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @ApiOperation(value = "Listar Categorias")
    public ResponseEntity<List<Categoria>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Categorias por Id")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Categorias")
    public ResponseEntity<CategoriaResponseDto> created(@RequestBody CategoriaRequestDto req, UriComponentsBuilder uriBuilder){
        Categoria categoria = service.create(req);
        return ResponseEntity
                .created(uriBuilder
                        .path("/categorias/{id}")
                        .buildAndExpand(categoria.getId())
                        .toUri())
                .body(CategoriaMapper.fromEntityResponse(categoria));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Categorias")
    public ResponseEntity<CategoriaResponseDto> update(@PathVariable Long id, @RequestBody CategoriaRequestDto request, UriComponentsBuilder uriBuilder){
        Categoria categoria = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/categorias/{id}")
                        .buildAndExpand(categoria.getId())
                        .toUri())
                .body(CategoriaMapper.fromEntityResponse(categoria));
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Categorias")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
