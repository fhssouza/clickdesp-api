package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.CidadeCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.CidadeResponse;
import com.souzatech.clickdesp.domain.service.CidadeService;
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
@RequestMapping("/cidades")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Cidades")
    public ResponseEntity<List<CidadeResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Cidades por Id")
    public ResponseEntity<CidadeResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Cidades")
    public ResponseEntity<CidadeResponse> create(@Valid @RequestBody CidadeCreateRequest request, UriComponentsBuilder uriBuilder) {
        CidadeResponse response = service.create(request);

        return ResponseEntity
                .created(uriBuilder
                    .path("/cidades/{id}")
                    .buildAndExpand(response.getId())
                    .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Cidades")
    public ResponseEntity<CidadeResponse> update(@PathVariable Long id, @Valid @RequestBody CidadeCreateRequest request, UriComponentsBuilder uriBuilder) {
        CidadeResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/cidades/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Cidades")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
