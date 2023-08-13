package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.repository.OrdemServicoRepository;
import com.souzatech.clickdesp.domain.service.CadastroOrdemServicoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordemservicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private CadastroOrdemServicoService cadatroService;

    @GetMapping
    public List<OrdemServico> listar(){
        return ordemServicoRepository.findAll();
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Long ordemServicoId){
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElse(null);

        if(ordemServico != null){
            return ResponseEntity.ok(ordemServico);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico salvar(@RequestBody OrdemServico ordemServico){
        return  cadatroService.salvar(ordemServico);
    }

    @PutMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServico> atualizar(@PathVariable Long ordemServicoId, @RequestBody OrdemServico ordemServico){
        OrdemServico ordemServicoAtual = ordemServicoRepository.findById(ordemServicoId).orElse(null);

        if(ordemServicoAtual != null){
            BeanUtils.copyProperties(ordemServico, ordemServicoAtual, "id");
            cadatroService.salvar(ordemServicoAtual);
            return  ResponseEntity.ok(ordemServicoAtual);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{ordemServicoId}")
    public ResponseEntity<?> remover(@PathVariable Long ordemServicoId){
        try{
            cadatroService.excluir(ordemServicoId);
            return ResponseEntity.noContent().build();

        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
