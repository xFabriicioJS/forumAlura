package com.alura.project.demo.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alura.project.demo.modelo.Usuario;
import com.alura.project.demo.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {


	private TokenService tokenService;
	private UsuarioRepository repository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository){
		this.tokenService = tokenService;
		this.repository = repository;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		//Verificando se o token recebido do cliente é valido.

		boolean valido = tokenService.isTokenValido(token);
		//retorna true se o token enviado é válido, e false se for inválido.

		if(valido){
			//"Autenticar" o usuário.
			autenticarCliente(token);
		}
		
		
		filterChain.doFilter(request, response);

		
	}

	private void autenticarCliente(String token) {
		//Recuperando o ID do usuário pelo token.
		Long idUsuario = tokenService.getIdUsuario(token);

		Usuario usuario = repository.findById(idUsuario).get();


		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
			String token = request.getHeader("Authorization");
		
			if(token == null || token.isBlank() || !token.startsWith("Bearer ")) {
				return null;
			}
			
			return token.substring(7, token.length());
	}

	
	
}
