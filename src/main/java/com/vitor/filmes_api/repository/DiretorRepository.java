package com.vitor.filmes_api.repository;

import com.vitor.filmes_api.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
}
