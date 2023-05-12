package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.exception.EntidadeEmUsoException;
import com.souzatech.clickdesp.domain.exception.EntidadeNaoEncontradaException;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.repository.CategoriaRepository;
import com.souzatech.clickdesp.domain.service.CadastroCategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CadastroCategoriaService cadatroService;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long categoriaId){
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElse(null);

        if(categoria != null){
            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria salvar(@RequestBody Categoria categoria){
        return  cadatroService.salvar(categoria);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria){
        Categoria categoriaAtual = categoriaRepository.findById(categoriaId).orElse(null);

        if(categoriaAtual != null){
            BeanUtils.copyProperties(categoria, categoriaAtual, "id");
            cadatroService.salvar(categoriaAtual);
            return  ResponseEntity.ok(categoriaAtual);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> remover(@PathVariable Long categoriaId){
        try{
            cadatroService.excluir(categoriaId);
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
