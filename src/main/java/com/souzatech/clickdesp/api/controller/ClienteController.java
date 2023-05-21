package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.exception.EntidadeEmUsoException;
import com.souzatech.clickdesp.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.clickdesp.domain.model.Cliente;
import com.souzatech.clickdesp.domain.repository.ClienteRepository;
import com.souzatech.clickdesp.domain.service.CadastroClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
         return cliente = cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        Cliente clienteAtual = clienteRepository.findById(clienteId).orElse(null);

        if (clienteAtual != null){
            BeanUtils.copyProperties(cliente, clienteAtual, "id", "endereco");
            cadastroClienteService.salvar(clienteAtual);
            return ResponseEntity.ok(clienteAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> remover(@PathVariable Long clienteId){
        try{
            cadastroClienteService.excluir(clienteId);
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
