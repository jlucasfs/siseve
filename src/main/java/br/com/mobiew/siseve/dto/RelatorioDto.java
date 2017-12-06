package br.com.mobiew.siseve.dto;

import java.io.Serializable;

public class RelatorioDto implements Serializable {

	private static final long serialVersionUID = -7990932958196663906L;

	private Long id;

	private String nome;

	private Long idade;

	public Long getId() {
		return this.id;
	}

	public RelatorioDto() {
		//
	}

	public RelatorioDto( Long idParam, String nomeParam, Long idadeParam ) {
		super();
		this.id = idParam;
		this.nome = nomeParam;
		this.idade = idadeParam;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nomeParam ) {
		this.nome = nomeParam;
	}

	public Long getIdade() {
		return this.idade;
	}

	public void setIdade( Long idadeParam ) {
		this.idade = idadeParam;
	}

	public void setId( Long idParam ) {
		this.id = idParam;
	}
}
