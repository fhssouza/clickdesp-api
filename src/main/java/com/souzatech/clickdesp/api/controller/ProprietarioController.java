package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponseDTO;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioRequestDTO;
import com.souzatech.clickdesp.domain.mapper.ProprietarioMapper;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
@Api(tags = "Proprietários")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @ApiOperation(value = "Listar Proprietários")
    public List<ProprietarioResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Proprietários por Id")
    public ResponseEntity<ProprietarioResponseDTO> findById(@PathVariable Long id){
        Proprietario entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(entity, ProprietarioResponseDTO.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Proprietários")
    public ResponseEntity<ProprietarioResponseDTO> create(@RequestBody ProprietarioRequestDTO dto, UriComponentsBuilder uriBuilder){
        Proprietario proprietario = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/proprietarios/{id}")
                        .buildAndExpand(proprietario.getId())
                        .toUri())
                .body(mapper.map(proprietario, ProprietarioResponseDTO.class));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Proprietários")
    public ResponseEntity<ProprietarioResponseDTO> update(@PathVariable Long id, @RequestBody ProprietarioResponseDTO dto, UriComponentsBuilder uriBuilder){
        Proprietario proprietario = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/proprietarios/{id}")
                        .buildAndExpand(proprietario.getId())
                        .toUri())
                .body(ProprietarioMapper.fromEntityDto(proprietario));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Proprietários")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
