package br.com.mobiew.siseve.dto;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import br.com.mobiew.siseve.model.enuns.StatusEnum;

public class FiltroDTO implements Serializable {

	private static final long serialVersionUID = 7912409913029575555L;

	private String nome;

	private Long idPaciente;

	private String nomePaciente;

	private String nomeProfissional;

	private String endereco;

	private Double valorIndice;

	private Double valorPago;

	private Double valorLancamento;

	private Integer quantidade;

	private InputStream inputStream;

	private String numeroCarteira;

	private String numeroGuia;

	private Integer numeroParcelas;

	private Date dataPagamento;

	private Date dataInicio;

	private Date dataFim;

	private Date dataAtendimento;

	private Boolean glosado;

	private StatusEnum status;

	private Double valorLaboratorio;

	private Double valorCompImplante;

	private Double valorNF;

	private String cnpj;

	private String mesReferencia;

	private String tipo;

	private PacienteDTO pacienteSelecionado;

	private Date dataRequisicao;

	private String motivo;

	private Long idProfissional;

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente( String nomePacienteParam ) {
		this.nomePaciente = nomePacienteParam;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco( String enderecoParam ) {
		this.endereco = enderecoParam;
	}

	public Double getValorIndice() {
		return this.valorIndice;
	}

	public void setValorIndice( Double valorIndiceParam ) {
		this.valorIndice = valorIndiceParam;
	}

	public Double getValorPago() {
		return this.valorPago;
	}

	public void setValorPago( Double valorPagoParam ) {
		this.valorPago = valorPagoParam;
	}

	public Double getValorLancamento() {
		return this.valorLancamento;
	}

	public void setValorLancamento( Double valorLancamentoParam ) {
		this.valorLancamento = valorLancamentoParam;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( Integer quantidadeParam ) {
		this.quantidade = quantidadeParam;
	}

	public InputStream getInputStream() {
		return this.inputStream;
	}

	public void setInputStream( InputStream inputStreamParam ) {
		this.inputStream = inputStreamParam;
	}

	public String getNumeroCarteira() {
		return this.numeroCarteira;
	}

	public void setNumeroCarteira( String numeroCarteiraParam ) {
		this.numeroCarteira = numeroCarteiraParam;
	}

	public String getNumeroGuia() {
		return this.numeroGuia;
	}

	public void setNumeroGuia( String numeroGuiaParam ) {
		this.numeroGuia = numeroGuiaParam;
	}

	public Integer getNumeroParcelas() {
		return this.numeroParcelas;
	}

	public void setNumeroParcelas( Integer numeroParcelasParam ) {
		this.numeroParcelas = numeroParcelasParam;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento( Date dataPagamentoParam ) {
		this.dataPagamento = dataPagamentoParam;
	}

	public Date getDataAtendimento() {
		return this.dataAtendimento;
	}

	public void setDataAtendimento( Date dataAtendimentoParam ) {
		this.dataAtendimento = dataAtendimentoParam;
	}

	public Boolean getGlosado() {
		return this.glosado;
	}

	public void setGlosado( Boolean glosadoParam ) {
		this.glosado = glosadoParam;
	}

	public StatusEnum getStatus() {
		return this.status;
	}

	public void setStatus( StatusEnum statusParam ) {
		this.status = statusParam;
	}

	public Double getValorLaboratorio() {
		return this.valorLaboratorio;
	}

	public void setValorLaboratorio( Double valorLaboratorioParam ) {
		this.valorLaboratorio = valorLaboratorioParam;
	}

	public Double getValorCompImplante() {
		return this.valorCompImplante;
	}

	public void setValorCompImplante( Double valorCompImplanteParam ) {
		this.valorCompImplante = valorCompImplanteParam;
	}

	public Double getValorNF() {
		return this.valorNF;
	}

	public void setValorNF( Double valorNFParam ) {
		this.valorNF = valorNFParam;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj( String cnpjParam ) {
		this.cnpj = cnpjParam;
	}

	public String getMesReferencia() {
		return this.mesReferencia;
	}

	public void setMesReferencia( String mesReferenciaParam ) {
		this.mesReferencia = mesReferenciaParam;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio( Date dataInicioParam ) {
		this.dataInicio = dataInicioParam;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim( Date dataFimParam ) {
		this.dataFim = dataFimParam;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nomeParam ) {
		this.nome = nomeParam;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipoParam ) {
		this.tipo = tipoParam;
	}

	public Long getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente( Long idPacienteParam ) {
		this.idPaciente = idPacienteParam;
	}

	public PacienteDTO getPacienteSelecionado() {
		return this.pacienteSelecionado;
	}

	public void setPacienteSelecionado( PacienteDTO pacienteSelecionadoParam ) {
		this.pacienteSelecionado = pacienteSelecionadoParam;
	}

	public Date getDataRequisicao() {
		return this.dataRequisicao;
	}

	public void setDataRequisicao( Date dataRequisicaoParam ) {
		this.dataRequisicao = dataRequisicaoParam;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo( String motivoParam ) {
		this.motivo = motivoParam;
	}

	public String getNomeProfissional() {
		return this.nomeProfissional;
	}

	public void setNomeProfissional( String nomeProfissionalParam ) {
		this.nomeProfissional = nomeProfissionalParam;
	}

	public Long getIdProfissional() {
		return this.idProfissional;
	}

	public void setIdProfissional( Long idProfissionalParam ) {
		this.idProfissional = idProfissionalParam;
	}

}
