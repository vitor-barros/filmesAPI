package com.vitor.filmes_api.controller;

import com.vitor.filmes_api.dto.DiretorDTO;
import com.vitor.filmes_api.model.Diretor;
import com.vitor.filmes_api.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @GetMapping
    public List<Diretor> listarTodos() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarPorId(@PathVariable Long id) {
        Optional<Diretor> diretor = diretorService.buscarPorId(id);
        return diretor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diretor criar(@RequestBody DiretorDTO dto) {
        Diretor diretor = new Diretor(
                dto.getNome(),
                dto.getNacionalidade(),
                dto.getNumeroIndicacoes(),
                dto.isAtivo()
        );
        return diretorService.salvar(diretor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizar(@PathVariable Long id, @RequestBody Diretor diretorAtualizado) {
        Optional<Diretor> diretorExistente = diretorService.buscarPorId(id);

        if (diretorExistente.isPresent()) {
            Diretor diretor = diretorExistente.get();
            diretor.setNome(diretorAtualizado.getNome());
            diretor.setNacionalidade(diretorAtualizado.getNacionalidade());
            diretor.setNumeroIndicacoes(diretorAtualizado.getNumeroIndicacoes());
            diretor.setAtivo(diretorAtualizado.isAtivo());
            Diretor salvo = diretorService.salvar(diretor);
            return ResponseEntity.ok(salvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Diretor> diretor = diretorService.buscarPorId(id);

        if (diretor.isPresent()) {
            diretorService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
