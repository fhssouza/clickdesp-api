package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.OrdemServicoDto;
import com.souzatech.clickdesp.domain.mapper.OrdemServicoMapper;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ordemservicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    public ResponseEntity<List<OrdemServico>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServico> create(@RequestBody OrdemServico ordemServico, UriComponentsBuilder uriBuilder){
        OrdemServico ordemServicoNova = service.create(ordemServico);
        return ResponseEntity
                .created(uriBuilder
                        .path("/ordemservicos/{id}")
                        .buildAndExpand(ordemServico.getId())
                        .toUri())
                .body(ordemServicoNova);
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
