package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.EnderecoCreateResquest;
import com.souzatech.clickdesp.domain.dto.response.EnderecoResponse;
import com.souzatech.clickdesp.domain.model.Endereco;
import com.souzatech.clickdesp.domain.service.EnderecoService;
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
@RequestMapping("/enderecos")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Endereços")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Endereços")
    public ResponseEntity<List<EnderecoResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Endereços por Id")
    public ResponseEntity<EnderecoResponse> findById(@PathVariable Long id){
        Endereco entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(entity, EnderecoResponse.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Endereços")
    public ResponseEntity<EnderecoResponse> create(@Valid @RequestBody EnderecoCreateResquest resquest, UriComponentsBuilder uriBuilder) {
        EnderecoResponse response = service.create(resquest);

        return ResponseEntity
                .created(uriBuilder
                    .path("/enderecos/{id}")
                    .buildAndExpand(response.getId())
                    .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Endereços")
    public ResponseEntity<EnderecoResponse> update(@PathVariable Long id, @Valid @RequestBody EnderecoCreateResquest request, UriComponentsBuilder uriBuilder) {
        EnderecoResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/enderecos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Endereços")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
