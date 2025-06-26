package com.vitor.filmes_api.dto;

public class FilmeDTO {
    private String titulo;
    private int anoLancamento;
    private String genero;
    private Long diretorId;

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

    public Long getDiretorId() {
        return diretorId;
    }

    public void setDiretorId(Long diretorId) {
        this.diretorId = diretorId;
    }
}
