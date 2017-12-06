package br.com.mobiew.siseve.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.mobiew.siseve.util.ConstantesData;

public class AtendimentoDTO implements Serializable {

	private static final long serialVersionUID = 655130280870957161L;

	private Long idAtendimento;

	private Date dataAtendimento;

	private Long idPaciente;

	private String nomePaciente;

	private String nomeProfissional;

	private String tipoAtendimento;

	private String convenio;

	private Integer numeroEvolucoes;

	private String tipoPlano;

	public Date getDataAtendimento() {
		return this.dataAtendimento;
	}

	public String getDataAtendimentoStr() {
		String result = "";
		if ( this.dataAtendimento != null ) {
			result = ConstantesData.FMT_DATA_HHMM.format( this.dataAtendimento );
		}
		return result;
	}

	public void setDataAtendimento( Date dataAtendimentoParam ) {
		this.dataAtendimento = dataAtendimentoParam;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente( String nomePacienteParam ) {
		this.nomePaciente = nomePacienteParam;
	}

	public String getTipoAtendimento() {
		return this.tipoAtendimento;
	}

	public void setTipoAtendimento( String tipoAtendimentoParam ) {
		this.tipoAtendimento = tipoAtendimentoParam;
	}

	public String getConvenio() {
		return this.convenio;
	}

	public void setConvenio( String convenioParam ) {
		this.convenio = convenioParam;
	}

	public Integer getNumeroEvolucoes() {
		return this.numeroEvolucoes;
	}

	public void setNumeroEvolucoes( Integer numeroEvolucoesParam ) {
		this.numeroEvolucoes = numeroEvolucoesParam;
	}

	public Long getIdAtendimento() {
		return this.idAtendimento;
	}

	public void setIdAtendimento( Long idAtendimentoParam ) {
		this.idAtendimento = idAtendimentoParam;
	}

	public Long getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente( Long idPacienteParam ) {
		this.idPaciente = idPacienteParam;
	}

	public String getTipoPlano() {
		return this.tipoPlano;
	}

	public void setTipoPlano( String tipoPlanoParam ) {
		this.tipoPlano = tipoPlanoParam;
	}

	public String getNomeProfissional() {
		return this.nomeProfissional;
	}

	public void setNomeProfissional( String nomeProfissionalParam ) {
		this.nomeProfissional = nomeProfissionalParam;
	}
}
