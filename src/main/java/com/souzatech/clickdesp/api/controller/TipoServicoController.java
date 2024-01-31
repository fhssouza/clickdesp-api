package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.TipoServicoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.TipoServicoResponse;
import com.souzatech.clickdesp.domain.model.TipoServico;
import com.souzatech.clickdesp.domain.service.TipoServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tiposervicos")
@Api(tags = "Tipo Serviços")
public class TipoServicoController {

    @Autowired
    private TipoServicoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @ApiOperation(value = "Listar Tipo de Servicos")
    public ResponseEntity<List<TipoServicoResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Tipo de Servicos por Id")
    public ResponseEntity<TipoServicoResponse> findById(@PathVariable Long id){
        TipoServico entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(entity, TipoServicoResponse.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Tipo de Servicos")
    public ResponseEntity<TipoServicoResponse> created(@Valid @RequestBody TipoServicoCreateRequest request, UriComponentsBuilder uriBuilder){
        TipoServicoResponse response = service.create(request);
        return ResponseEntity
                .created(uriBuilder
                        .path("/tiposervicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Tipo de Servicos")
    public ResponseEntity<TipoServicoResponse> update(@PathVariable Long id, @Valid @RequestBody TipoServicoCreateRequest request, UriComponentsBuilder uriBuilder){
        TipoServicoResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/tiposervicos/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Tipo de Servicos")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
