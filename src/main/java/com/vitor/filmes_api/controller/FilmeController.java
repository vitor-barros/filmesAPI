package com.vitor.filmes_api.controller;

import com.vitor.filmes_api.dto.FilmeDTO;
import com.vitor.filmes_api.model.Diretor;
import com.vitor.filmes_api.model.Filme;
import com.vitor.filmes_api.service.DiretorService;
import com.vitor.filmes_api.service.FilmeService;
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
    public ResponseEntity<Filme> criar(@RequestBody FilmeDTO dto) {
        Optional<Diretor> diretorOptional = diretorService.buscarPorId(dto.getDiretorId());

        if (diretorOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Filme filme = new Filme(
                dto.getTitulo(),
                dto.getAnoLancamento(),
                dto.getGenero(),
                diretorOptional.get()
        );

        return ResponseEntity.ok(filmeService.salvar(filme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id, @RequestBody FilmeDTO dto) {
        Optional<Filme> filmeExistente = filmeService.buscarPorId(id);

        if (filmeExistente.isPresent()) {
            Filme filme = filmeExistente.get();
            filme.setTitulo(dto.getTitulo());
            filme.setAnoLancamento(dto.getAnoLancamento());
            filme.setGenero(dto.getGenero());

            Optional<Diretor> diretorOptional = diretorService.buscarPorId(dto.getDiretorId());
            if (diretorOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            filme.setDiretor(diretorOptional.get());

            return ResponseEntity.ok(filmeService.salvar(filme));
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
