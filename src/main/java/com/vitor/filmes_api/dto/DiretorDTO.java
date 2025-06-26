package com.vitor.filmes_api.dto;

public class DiretorDTO {
    private String nome;
    private String nacionalidade;
    private int numeroIndicacoes;
    private boolean ativo;

    public DiretorDTO() {
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
}
