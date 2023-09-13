package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.response.ServicoResponseDTO;
import com.souzatech.clickdesp.domain.dto.request.ServicoCreateDTO;
import com.souzatech.clickdesp.domain.dto.request.ServicoUpdateDTO;
import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.service.ServicoService;
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
@RequestMapping("/servicos")
@Api(tags = "Serviços")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Listar Serviços")
    public ResponseEntity<List<ServicoResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Servicos por Id")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id){
        Servico entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(entity, ServicoResponseDTO.class));
    }

    @PostMapping
    @ApiOperation(value = "Criar Servicos")
    public ResponseEntity<ServicoResponseDTO> create(@Valid @RequestBody ServicoCreateDTO dto, UriComponentsBuilder uriBuilder) {
        Servico entity = service.create(dto);

        return ResponseEntity
                .created(uriBuilder
                    .path("/servicos/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri())
                .body(modelMapper.map(entity, ServicoResponseDTO.class));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Servicos")
    public ResponseEntity<ServicoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ServicoUpdateDTO dto, UriComponentsBuilder uriBuilder) {
        Servico entity = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/servicos/{id}")
                        .buildAndExpand(entity.getId())
                        .toUri())
                .body(modelMapper.map(entity, ServicoResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Servicos")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
