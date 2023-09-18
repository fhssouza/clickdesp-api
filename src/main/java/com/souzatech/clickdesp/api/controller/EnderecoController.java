package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.EnderecoResquestDTO;
import com.souzatech.clickdesp.domain.dto.response.EnderecoResponseDTO;
import com.souzatech.clickdesp.domain.model.Endereco;
import com.souzatech.clickdesp.domain.service.EnderecoService;
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
@RequestMapping("/enderecos")
@Api(tags = "Endereços")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Endereços")
    public ResponseEntity<List<EnderecoResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Endereços por Id")
    public ResponseEntity<EnderecoResponseDTO> findById(@PathVariable Long id){
        Endereco entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(entity, EnderecoResponseDTO.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Endereços")
    public ResponseEntity<EnderecoResponseDTO> create(@Valid @RequestBody EnderecoResquestDTO dto, UriComponentsBuilder uriBuilder) {
        Endereco entity = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                    .path("/enderecos/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri())
                .body(modelMapper.map(entity, EnderecoResponseDTO.class));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Endereços")
    public ResponseEntity<EnderecoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EnderecoResquestDTO dto, UriComponentsBuilder uriBuilder) {
        Endereco entity = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/enderecos/{id}")
                        .buildAndExpand(entity.getId())
                        .toUri())
                .body(modelMapper.map(entity, EnderecoResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Endereços")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
