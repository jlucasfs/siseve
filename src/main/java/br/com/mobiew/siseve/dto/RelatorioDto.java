package br.com.mobiew.siseve.dto;

import java.io.Serializable;

public class RelatorioDto implements Serializable {

	private static final long serialVersionUID = -7990932958196663906L;

	private Long id;

	private String local;

	private String nome;

	private Long idade;

	private String endereco;

	private String telefone;

	private String email;

	private String religiao;

	private Long qtdeOdontologia;

	private Long qtdeAdvogado;

	private Long qtdeFisioterapia;

	private Long qtdePediatria;

	private Long qtdeGinecologia;

	private Long qtdeClinicaGeral;

	private Long qtdeCorteCabelo;

	public Long getId() {
		return this.id;
	}

	public RelatorioDto() {
		//
	}
	//
	// public RelatorioDto( Long idParam, String nomeParam, Long idadeParam ) {
	// super();
	// this.id = idParam;
	// this.nome = nomeParam;
	// this.idade = idadeParam;
	// this.endereco = "RUA AAAAAAA";
	// this.local = "IGREJA BATISTA MARE";
	// this.qtdeAdvogado = 1L;
	// this.qtdeClinicaGeral = 0L;
	// this.qtdeCorteCabelo = 1L;
	// this.qtdePediatria = 0L;
	// this.qtdeFisioterapia = 0L;
	// this.qtdeGinecologia = 1L;
	// this.qtdeOdontologia = 1L;
	// this.email = "aaaaaa@bbbb.com";
	// }

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

	public Long getQtdeOdontologia() {
		return this.qtdeOdontologia;
	}

	public void setQtdeOdontologia( Long qtdeOdontologiaParam ) {
		this.qtdeOdontologia = qtdeOdontologiaParam;
	}

	public Long getQtdeAdvogado() {
		return this.qtdeAdvogado;
	}

	public void setQtdeAdvogado( Long qtdeAdvogadoParam ) {
		this.qtdeAdvogado = qtdeAdvogadoParam;
	}

	public Long getQtdePediatria() {
		return this.qtdePediatria;
	}

	public void setQtdePediatria( Long qtdePediatriaParam ) {
		this.qtdePediatria = qtdePediatriaParam;
	}

	public Long getQtdeGinecologia() {
		return this.qtdeGinecologia;
	}

	public void setQtdeGinecologia( Long qtdeGinecologiaParam ) {
		this.qtdeGinecologia = qtdeGinecologiaParam;
	}

	public Long getQtdeClinicaGeral() {
		return this.qtdeClinicaGeral;
	}

	public void setQtdeClinicaGeral( Long qtdeClinicaGeralParam ) {
		this.qtdeClinicaGeral = qtdeClinicaGeralParam;
	}

	public Long getQtdeCorteCabelo() {
		return this.qtdeCorteCabelo;
	}

	public void setQtdeCorteCabelo( Long qtdeCorteCabeloParam ) {
		this.qtdeCorteCabelo = qtdeCorteCabeloParam;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone( String telefoneParam ) {
		this.telefone = telefoneParam;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail( String emailParam ) {
		this.email = emailParam;
	}

	public String getReligiao() {
		return this.religiao;
	}

	public void setReligiao( String religiaoParam ) {
		this.religiao = religiaoParam;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal( String localParam ) {
		this.local = localParam;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco( String enderecoParam ) {
		this.endereco = enderecoParam;
	}

	public Long getQtdeFisioterapia() {
		return this.qtdeFisioterapia;
	}

	public void setQtdeFisioterapia( Long qtdeFisioterapiaParam ) {
		this.qtdeFisioterapia = qtdeFisioterapiaParam;
	}

}
