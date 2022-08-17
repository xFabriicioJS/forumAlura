package com.alura.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.project.demo.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
