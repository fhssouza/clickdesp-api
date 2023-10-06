package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.OrdemServicoDto;
import com.souzatech.clickdesp.domain.dto.request.OrdemServicoRequestDTO;
import com.souzatech.clickdesp.domain.mapper.OrdemServicoMapper;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.service.OrdemServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ordensservico")
@Api(tags = "Ordem de Serviço")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    @ApiOperation(value = "Listar Ordens de Serviço")
    public ResponseEntity<List<OrdemServico>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Ordens de Serviço por Id")
    public ResponseEntity<OrdemServico> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Ordem de Serviço")
    public ResponseEntity<OrdemServico> create(@RequestBody OrdemServicoRequestDTO dto, UriComponentsBuilder uriBuilder){
        OrdemServico entity = service.create(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(entity.getId())
                        .toUri())
                .body(entity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Ordem de Serviço")
    public ResponseEntity<OrdemServicoDto> update(@PathVariable Long id, @RequestBody OrdemServicoDto dto, UriComponentsBuilder uriBuilder){
        OrdemServico ordemServico = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(ordemServico.getId())
                        .toUri())
                .body(OrdemServicoMapper.fromEntityDto(ordemServico));

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Ordem de Serviço")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
