package com.alura.project.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.project.demo.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

		Optional<Usuario> findByEmail(String email);
	
}
