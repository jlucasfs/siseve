package br.com.mobiew.siseve.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Atendimento generated by hbm2java
 */
@Entity
@Table( name = "atendimento" )
public class Atendimento implements Serializable {

	private static final long serialVersionUID = -1297433269605983729L;

	private Long id;

	private Cliente cliente;

	private Profissional profissional;

	private Servico servico;

	private Date dataAtendimento;

	private String observacao;

	public Atendimento() {
	}

	public Atendimento( Cliente cliente, Servico servico, Date dataAtendimento ) {
		this.cliente = cliente;
		this.servico = servico;
		this.dataAtendimento = dataAtendimento;
	}

	public Atendimento( Cliente cliente, Profissional profissional, Servico servico, Date dataAtendimento, String observacao ) {
		this.cliente = cliente;
		this.profissional = profissional;
		this.servico = servico;
		this.dataAtendimento = dataAtendimento;
		this.observacao = observacao;
	}

	@Id
	@GeneratedValue( strategy = IDENTITY )

	@Column( name = "ID", unique = true, nullable = false )
	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "ID_CLIENTE", nullable = false )
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente( Cliente cliente ) {
		this.cliente = cliente;
	}

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "ID_PROFISSIONAL" )
	public Profissional getProfissional() {
		return this.profissional;
	}

	public void setProfissional( Profissional profissional ) {
		this.profissional = profissional;
	}

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "ID_SERVICO", nullable = false )
	public Servico getServico() {
		return this.servico;
	}

	public void setServico( Servico servico ) {
		this.servico = servico;
	}

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DATA_ATENDIMENTO", nullable = false, length = 19 )
	public Date getDataAtendimento() {
		return this.dataAtendimento;
	}

	public void setDataAtendimento( Date dataAtendimento ) {
		this.dataAtendimento = dataAtendimento;
	}

	@Column( name = "OBSERVACAO", length = 4000 )
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

}
