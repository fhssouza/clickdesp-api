package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.exception.EntidadeEmUsoException;
import com.souzatech.clickdesp.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.repository.ProprietarioRepository;
import com.souzatech.clickdesp.domain.service.CadastroProprietarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Autowired
    private CadastroProprietarioService cadastroProprietarioService;

    @GetMapping
    public List<Proprietario> listar(){
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long clienteId){
        Proprietario proprietario = proprietarioRepository.findById(clienteId).orElse(null);

        if(proprietario != null){
            return ResponseEntity.ok().body(proprietario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario salvar(@RequestBody Proprietario proprietario){
         return proprietario = cadastroProprietarioService.salvar(proprietario);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long clienteId, @RequestBody Proprietario proprietario){
        Proprietario proprietarioAtual = proprietarioRepository.findById(clienteId).orElse(null);

        if (proprietarioAtual != null){
            BeanUtils.copyProperties(proprietario, proprietarioAtual, "id", "endereco");
            cadastroProprietarioService.salvar(proprietarioAtual);
            return ResponseEntity.ok(proprietarioAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> remover(@PathVariable Long clienteId){
        try{
            cadastroProprietarioService.excluir(clienteId);
            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
