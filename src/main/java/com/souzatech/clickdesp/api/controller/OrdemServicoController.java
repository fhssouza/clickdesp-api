package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.CreateOrdemServicoRequest;
import com.souzatech.clickdesp.domain.dto.response.OrdemServicoResponse;
import com.souzatech.clickdesp.domain.service.OrdemServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ordens-servicos")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Ordem de Serviço")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    @ApiOperation(value = "Listar Ordens de Serviço")
    public ResponseEntity<List<OrdemServicoResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Ordens de Serviço por Id")
    public ResponseEntity<OrdemServicoResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Ordem de Serviço")
    public ResponseEntity<OrdemServicoResponse> create(@RequestBody CreateOrdemServicoRequest request, UriComponentsBuilder uriBuilder){
        OrdemServicoResponse response = service.create(request);
        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Ordem de Serviço")
    public ResponseEntity<OrdemServicoResponse> update(@PathVariable Long id, @RequestBody CreateOrdemServicoRequest request, UriComponentsBuilder uriBuilder){
        OrdemServicoResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }


    @PutMapping("/{id}/finalizada")
    @ApiOperation(value = "Finalizar a Ordem de Serviço")
    public ResponseEntity<OrdemServicoResponse> finish(@PathVariable Long id, UriComponentsBuilder uriBuilder){
        OrdemServicoResponse response = service.finish(id);

        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}/cancelada")
    @ApiOperation(value = "Cancelar a Ordem de Serviço")
    public ResponseEntity<OrdemServicoResponse> cancel(@PathVariable Long id, UriComponentsBuilder uriBuilder){
        OrdemServicoResponse response = service.cancel(id);

        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }
}
