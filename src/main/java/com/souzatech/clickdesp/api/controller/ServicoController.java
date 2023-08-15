package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.ServicoDto;
import com.souzatech.clickdesp.domain.mapper.ServicoMapper;
import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.repository.ServicoRepository;
import com.souzatech.clickdesp.domain.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<Servico>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ServicoDto> create(@RequestBody ServicoDto dto, UriComponentsBuilder uriBuilder) {
        Servico servico = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                    .path("/servicos/{id}")
                    .buildAndExpand(servico.getId())
                    .toUri())
                .body(ServicoMapper.fromEntityDto(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDto> update(@PathVariable Long id, @RequestBody ServicoDto dto, UriComponentsBuilder uriBuilder) {
        Servico servico = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/servicos/{id}")
                        .buildAndExpand(servico.getId())
                        .toUri())
                .body(ServicoMapper.fromEntityDto(servico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
