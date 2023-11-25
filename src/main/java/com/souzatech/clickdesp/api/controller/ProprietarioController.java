package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponse;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioCreateRequest;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/proprietarios")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Proprietários")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @ApiOperation(value = "Listar Proprietários")
    public List<ProprietarioResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Proprietários por Id")
    public ResponseEntity<ProprietarioResponse> findById(@PathVariable Long id){
        Proprietario entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(entity, ProprietarioResponse.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Proprietários")
    public ResponseEntity<ProprietarioResponse> create(@Valid @RequestBody ProprietarioCreateRequest request, UriComponentsBuilder uriBuilder){
        ProprietarioResponse response = service.create(request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/proprietarios/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Proprietários")
    public ResponseEntity<ProprietarioResponse> update(@PathVariable Long id, @RequestBody ProprietarioCreateRequest request, UriComponentsBuilder uriBuilder){
        ProprietarioResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/proprietarios/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Proprietários")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
