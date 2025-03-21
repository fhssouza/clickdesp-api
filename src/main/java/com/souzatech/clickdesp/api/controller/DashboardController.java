package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioPorMesResponse;
import com.souzatech.clickdesp.domain.service.OrdemServicoService;
import com.souzatech.clickdesp.domain.service.ProprietarioService;
import com.souzatech.clickdesp.domain.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getEstatistica() {
        Map<String, Long> estatistica = new HashMap<>();
        estatistica.put("totalVeiculos", veiculoService.countVeiculo());
        estatistica.put("totalProprietarios", proprietarioService.countProprietario());
        estatistica.put("totalOrdemServico", ordemServicoService.countOrdemServico());
        return ResponseEntity.ok(estatistica);
    }

    @GetMapping("/proprietarios-por-mes")
    public ResponseEntity<List<ProprietarioPorMesResponse>> getProprietarioPorMes() {
        return ResponseEntity.ok(proprietarioService.countProprietariosPorMes());
    }
}
