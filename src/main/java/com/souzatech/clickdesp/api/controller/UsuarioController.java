package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.UsuarioCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.UsuarioResponse;
import com.souzatech.clickdesp.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @ApiOperation(value = "Listar Usuários")
    public ResponseEntity<List<UsuarioResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar Usuário por Id")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Criar Usuário")
    public ResponseEntity<UsuarioResponse> created(@Valid @RequestBody UsuarioCreateRequest request, UriComponentsBuilder uriBuilder) throws MessagingException, UnsupportedEncodingException {
        UsuarioResponse response = service.create(request);
        return ResponseEntity
                .created(uriBuilder
                        .path("/usuarios/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Usuário")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @RequestBody UsuarioCreateRequest request, UriComponentsBuilder uriBuilder){
        UsuarioResponse response = service.update(id, request);

        return ResponseEntity
                .created(uriBuilder
                        .path("/usuarios/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Usuario")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
