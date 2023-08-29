package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.UsuarioDTO;
import com.souzatech.clickdesp.domain.mapper.UsuarioMapper;
import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @ApiOperation(value = "Listar Usuários")
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Usuário por Id")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Usuário")
    public ResponseEntity<UsuarioDTO> created(@Valid @RequestBody UsuarioDTO dto, UriComponentsBuilder uriBuilder){
        Usuario usuario = service.create(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path("/usuarios/{id}")
                        .buildAndExpand(usuario.getId())
                        .toUri())
                .body(UsuarioMapper.fromEntityDto(usuario));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Usuário")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto, UriComponentsBuilder uriBuilder){
        Usuario usuario = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/usuarios/{id}")
                        .buildAndExpand(usuario.getId())
                        .toUri())
                .body(UsuarioMapper.fromEntityDto(usuario));
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Usuario")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
