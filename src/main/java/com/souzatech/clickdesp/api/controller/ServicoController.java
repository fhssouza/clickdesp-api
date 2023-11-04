package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.ServicoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.ServicoResponse;
import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.service.ServicoService;
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
@RequestMapping("/servicos")
@Consumes("MediaType.APPLICATION_JSON")
@Produces("MediaType.APPLICATION_JSON")
@Api(tags = "Serviços")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Serviços")
    public ResponseEntity<List<ServicoResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Servicos por Id")
    public ResponseEntity<ServicoResponse> findById(@PathVariable Long id){
        Servico response = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(response, ServicoResponse.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Servicos")
    public ResponseEntity<ServicoResponse> create(@Valid @RequestBody ServicoCreateRequest request, UriComponentsBuilder uriBuilder) {
        ServicoResponse response = service.create(request);

        return ResponseEntity
                .created(uriBuilder
                    .path("/servicos/{id}")
                    .buildAndExpand(response.getId())
                    .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Servicos")
    public ResponseEntity<ServicoResponse> update(@PathVariable Long id, @Valid @RequestBody ServicoCreateRequest request, UriComponentsBuilder uriBuilder) {
        ServicoResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/servicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Servicos")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
