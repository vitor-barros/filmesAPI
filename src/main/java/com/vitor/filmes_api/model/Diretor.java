package com.vitor.filmes_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nacionalidade;
    private int numeroIndicacoes;
    private boolean ativo;

    @OneToMany(mappedBy = "diretor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Filme> filmes;

    public Diretor() {
    }

    public Diretor(String nome, String nacionalidade, int numeroIndicacoes, boolean ativo) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.numeroIndicacoes = numeroIndicacoes;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getNumeroIndicacoes() {
        return numeroIndicacoes;
    }

    public void setNumeroIndicacoes(int numeroIndicacoes) {
        this.numeroIndicacoes = numeroIndicacoes;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
