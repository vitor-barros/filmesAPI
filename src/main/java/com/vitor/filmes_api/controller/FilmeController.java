package com.vitor.filmes_api.controller;

import com.vitor.filmes_api.model.Filme;
import com.vitor.filmes_api.service.FilmeService;
import com.vitor.filmes_api.service.DiretorService;
import com.vitor.filmes_api.model.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private DiretorService diretorService;

    @GetMapping
    public List<Filme> listarTodos() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        Optional<Filme> filme = filmeService.buscarPorId(id);
        return filme.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Filme> criar(@RequestBody Filme filme) {
        // Verifica se o diretor existe antes de salvar o filme
        if (filme.getDiretor() != null) {
            Optional<Diretor> diretor = diretorService.buscarPorId(filme.getDiretor().getId());
            if (diretor.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            filme.setDiretor(diretor.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

        Filme salvo = filmeService.salvar(filme);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        Optional<Filme> filmeExistente = filmeService.buscarPorId(id);

        if (filmeExistente.isPresent()) {
            Filme filme = filmeExistente.get();
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            filme.setGenero(filmeAtualizado.getGenero());

            if (filmeAtualizado.getDiretor() != null) {
                Optional<Diretor> diretor = diretorService.buscarPorId(filmeAtualizado.getDiretor().getId());
                if (diretor.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }
                filme.setDiretor(diretor.get());
            }

            Filme salvo = filmeService.salvar(filme);
            return ResponseEntity.ok(salvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Filme> filme = filmeService.buscarPorId(id);

        if (filme.isPresent()) {
            filmeService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
