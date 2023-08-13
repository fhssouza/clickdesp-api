package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.repository.ServicoRepository;
import com.souzatech.clickdesp.domain.service.CadastroServicoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private CadastroServicoService cadatroService;

    @GetMapping
    public List<Servico> listar(){
        return servicoRepository.findAll();
    }

    @GetMapping("/{servicoId}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long servicoId){
        Servico servico = servicoRepository.findById(servicoId)
                .orElse(null);

        if(servico != null){
            return ResponseEntity.ok(servico);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvar(@RequestBody Servico servico){
        return  cadatroService.salvar(servico);
    }

    @PutMapping("/{servicoId}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long servicoId, @RequestBody Servico servico){
        Servico servicoAtual = servicoRepository.findById(servicoId).orElse(null);

        if(servicoAtual != null){
            BeanUtils.copyProperties(servico, servicoAtual, "id");
            cadatroService.salvar(servicoAtual);
            return  ResponseEntity.ok(servicoAtual);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{servicoId}")
    public ResponseEntity<?> remover(@PathVariable Long servicoId){
        try{
            cadatroService.excluir(servicoId);
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
