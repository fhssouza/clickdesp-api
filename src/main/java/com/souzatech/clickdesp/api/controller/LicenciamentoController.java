package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.LicenciamentoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.LicenciamentoResponse;
import com.souzatech.clickdesp.domain.service.LicenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licenciamentos")
public class LicenciamentoController {

    private final LicenciamentoService licenciamentoService;

    @Autowired
    public LicenciamentoController(LicenciamentoService licenciamentoService) {
        this.licenciamentoService = licenciamentoService;
    }

    @GetMapping
    public ResponseEntity<List<LicenciamentoResponse>> findAll() {
        List<LicenciamentoResponse> licenciamentos = licenciamentoService.findAll();
        return ResponseEntity.ok(licenciamentos);
    }

    @GetMapping("/ano/{anoReferencia}")
    public ResponseEntity<List<LicenciamentoResponse>> findByAnoReferencia(@PathVariable Integer anoReferencia) {
        List<LicenciamentoResponse> response = licenciamentoService.findByanoReferencia(anoReferencia);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<LicenciamentoResponse> create(@RequestBody LicenciamentoCreateRequest request) {
        LicenciamentoResponse response = licenciamentoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicenciamentoResponse> update(@PathVariable Long id, @RequestBody LicenciamentoCreateRequest request) {
        LicenciamentoResponse response = licenciamentoService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        licenciamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
