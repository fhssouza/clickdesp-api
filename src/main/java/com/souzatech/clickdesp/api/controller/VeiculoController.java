package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Veiculo;
import com.souzatech.clickdesp.domain.repository.VeiculoRepository;
import com.souzatech.clickdesp.domain.service.CadastroVeiculoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CadastroVeiculoService cadatroService;

    @GetMapping
    public List<Veiculo> listar(){
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long veiculoId){
        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElse(null);

        if(veiculo != null){
            return ResponseEntity.ok(veiculo);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo salvar(@RequestBody Veiculo veiculo){
        return  cadatroService.salvar(veiculo);
    }

    @PutMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long veiculoId, @RequestBody Veiculo veiculo){
        Veiculo veiculoAtual = veiculoRepository.findById(veiculoId).orElse(null);

        if(veiculoAtual != null){
            BeanUtils.copyProperties(veiculo, veiculoAtual, "id");
            cadatroService.salvar(veiculoAtual);
            return  ResponseEntity.ok(veiculoAtual);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{veiculoId}")
    public ResponseEntity<?> remover(@PathVariable Long veiculoId){
        try{
            cadatroService.excluir(veiculoId);
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
