package com.vitor.filmes_api.repository;

import com.vitor.filmes_api.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
