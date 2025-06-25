package com.vitor.filmes_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int anoLancamento;
    private String genero;

    @ManyToOne
    @JoinColumn(name = "diretor_id") // nome da coluna FK no banco
    @JsonBackReference
    private Diretor diretor;

    public Filme() {
    }

    public Filme(String titulo, int anoLancamento, String genero, Diretor diretor) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.diretor = diretor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }
}
