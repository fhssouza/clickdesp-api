package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.EstadoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.EstadoResponse;
import com.souzatech.clickdesp.domain.model.Estado;
import com.souzatech.clickdesp.domain.service.EstadoService;
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
@RequestMapping("/estados")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Estados")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Estados")
    public ResponseEntity<List<EstadoResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Estados por Id")
    public ResponseEntity<EstadoResponse> findById(@PathVariable Long id){
        Estado response = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(response, EstadoResponse.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Estados")
    public ResponseEntity<EstadoResponse> create(@Valid @RequestBody EstadoCreateRequest request, UriComponentsBuilder uriBuilder){
        EstadoResponse response = service.create(request);
        return ResponseEntity
                .created(uriBuilder
                        .path("/estados/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Estados")
    public ResponseEntity<EstadoResponse> update(@PathVariable Long id, @Valid @RequestBody EstadoCreateRequest request, UriComponentsBuilder uriBuilder){
        EstadoResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/estados/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Estados")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
