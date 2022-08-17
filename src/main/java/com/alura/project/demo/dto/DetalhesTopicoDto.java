package com.alura.project.demo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.project.demo.modelo.StatusTopico;
import com.alura.project.demo.modelo.Topico;

public class DetalhesTopicoDto {
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String autor;
	private StatusTopico status;
	private List<RespostaDto> respostas;
	
	public DetalhesTopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.autor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<>();
		respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));		
	
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getAutor() {
		return autor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}
	
	

}
