package com.alura.project.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.project.demo.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	
	
	Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

}
