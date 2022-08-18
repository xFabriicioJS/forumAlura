package com.alura.project.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.project.demo.config.security.TokenService;
import com.alura.project.demo.dto.TokenDto;
import com.alura.project.demo.form.LoginForm;

@RestController()
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin); //Retorna uma variavel do tipo "Authentication"
			
			String token = tokenService.gerarToken(authentication);
			
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch(AuthenticationException error) {
			return ResponseEntity.badRequest().build();
		}
		
		
	}
	
}
