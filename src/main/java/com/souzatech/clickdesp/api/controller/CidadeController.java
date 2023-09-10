package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.CidadeCreateDTO;
import com.souzatech.clickdesp.domain.dto.response.CidadeDTO;
import com.souzatech.clickdesp.domain.dto.request.CidadeUpdateDTO;
import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.service.CidadeService;
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
@RequestMapping("/cidades")
@Api(tags = "Cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Cidades")
    public ResponseEntity<List<CidadeDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Cidades por Id")
    public ResponseEntity<CidadeDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Cidades")
    public ResponseEntity<CidadeDTO> create(@Valid @RequestBody CidadeCreateDTO request, UriComponentsBuilder uriBuilder) {
        Cidade cidade = service.create(request);

        return ResponseEntity
                .created(uriBuilder
                    .path("/cidades/{id}")
                    .buildAndExpand(cidade.getId())
                    .toUri())
                .body(modelMapper.map(cidade, CidadeDTO.class));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Cidades")
    public ResponseEntity<CidadeDTO> update(@PathVariable Long id, @Valid @RequestBody CidadeUpdateDTO dto, UriComponentsBuilder uriBuilder) {
        Cidade cidade = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/cidades/{id}")
                        .buildAndExpand(cidade.getId())
                        .toUri())
                .body(modelMapper.map(cidade, CidadeDTO.class));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Cidades")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
