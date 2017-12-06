package br.com.mobiew.siseve.dto;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class PacienteEmailDto implements Serializable {

	private static final long serialVersionUID = 4557867422596816593L;

	private String titulo;

	private String email;

	private String mensagem;

	private File file;

	private Long idPaciente;

	private Date dataPrevistaEnvio;

	private Long idEvento;

	private String observacao;

	public PacienteEmailDto() {
		//
	}

	public PacienteEmailDto( String tituloParam, String emailParam, String mensagemParam, Long idPacienteParam, String observacaoParam,
			Date dataPrevistaEnvioParam ) {
		super();
		this.titulo = tituloParam;
		this.email = emailParam;
		this.mensagem = mensagemParam;
		this.idPaciente = idPacienteParam;
		this.observacao = observacaoParam;
		this.dataPrevistaEnvio = dataPrevistaEnvioParam;
	}

	public PacienteEmailDto( String tituloParam, String emailParam, String mensagemParam, File fileParam ) {
		super();
		this.titulo = tituloParam;
		this.email = emailParam;
		this.mensagem = mensagemParam;
		this.file = fileParam;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo( String tituloParam ) {
		this.titulo = tituloParam;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail( String emailParam ) {
		this.email = emailParam;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem( String mensagemParam ) {
		this.mensagem = mensagemParam;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile( File fileParam ) {
		this.file = fileParam;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.email == null ) ? 0 : this.email.hashCode() );
		result = prime * result + ( ( this.mensagem == null ) ? 0 : this.mensagem.hashCode() );
		result = prime * result + ( ( this.titulo == null ) ? 0 : this.titulo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		PacienteEmailDto other = (PacienteEmailDto) obj;
		if ( this.email == null ) {
			if ( other.email != null ) return false;
		} else if ( !this.email.equals( other.email ) ) return false;
		if ( this.mensagem == null ) {
			if ( other.mensagem != null ) return false;
		} else if ( !this.mensagem.equals( other.mensagem ) ) return false;
		if ( this.titulo == null ) {
			if ( other.titulo != null ) return false;
		} else if ( !this.titulo.equals( other.titulo ) ) return false;
		return true;
	}

	public Long getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente( Long idPacienteParam ) {
		this.idPaciente = idPacienteParam;
	}

	public Date getDataPrevistaEnvio() {
		return this.dataPrevistaEnvio;
	}

	public void setDataPrevistaEnvio( Date dataPrevistaEnvioParam ) {
		this.dataPrevistaEnvio = dataPrevistaEnvioParam;
	}

	public Long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento( Long idEventoParam ) {
		this.idEvento = idEventoParam;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacaoParam ) {
		this.observacao = observacaoParam;
	}
}
