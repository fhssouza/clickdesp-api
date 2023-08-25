package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.UsuarioDTO;
import com.souzatech.clickdesp.domain.mapper.UsuarioMapper;
import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> created(@RequestBody UsuarioDTO dto, UriComponentsBuilder uriBuilder){
        Usuario categoria = service.create(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path("/usuarios/{id}")
                        .buildAndExpand(categoria.getId())
                        .toUri())
                .body(UsuarioMapper.fromEntityDto(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto, UriComponentsBuilder uriBuilder){
        Usuario categoria = service.update(id, dto);

        return ResponseEntity
                .created(uriBuilder
                        .path("/usuarios/{id}")
                        .buildAndExpand(categoria.getId())
                        .toUri())
                .body(UsuarioMapper.fromEntityDto(categoria));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
