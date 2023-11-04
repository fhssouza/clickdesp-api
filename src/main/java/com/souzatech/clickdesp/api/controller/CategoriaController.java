package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.CategoriaCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.CategoriaResponse;
import com.souzatech.clickdesp.domain.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@Api(tags = "Categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @ApiOperation(value = "Listar Categorias")
    public ResponseEntity<List<CategoriaResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Categorias por Id")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Categorias")
    public ResponseEntity<CategoriaResponse> created(@Valid @RequestBody CategoriaCreateRequest request, UriComponentsBuilder uriBuilder){
        CategoriaResponse response = service.create(request);
        return ResponseEntity
                .created(uriBuilder
                        .path("/categorias/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Categorias")
    public ResponseEntity<CategoriaResponse> update(@PathVariable Long id, @Valid @RequestBody CategoriaCreateRequest request, UriComponentsBuilder uriBuilder){
        CategoriaResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/categorias/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Categorias")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
