package br.com.mobiew.siseve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.dto.AtendimentoDTO;
import br.com.mobiew.siseve.model.entity.Atendimento;
import br.com.mobiew.siseve.model.entity.Cliente;
import br.com.mobiew.siseve.service.AtendimentoService;
import br.com.mobiew.siseve.service.ClienteService;
import br.com.mobiew.siseve.util.scopes.Scopes;

@Controller
@Scope( Scopes.SESSION )
public class AtendimentoController {

	private static final Logger LOG = Logger.getLogger( AtendimentoController.class );

	private String tituloTela = "Atendimentos";

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private ClienteService clienteService;

	private List<Cliente> clientes;

	private List<Cliente> listaCliente;

	private Cliente cliente = new Cliente();

	private String nomePaciente;

	private Date dataAtual = new Date();

	private boolean chamadaDePaciente;

	private List<AtendimentoDTO> atendimentos;

	private Cliente pacienteSelecionado;

	private Long idCliente;

	private List<Atendimento> listaAtendimentos;

	@PostConstruct
	public void inicializar() {
		this.cliente = new Cliente();
		this.nomePaciente = null;
		this.listaCliente = this.clienteService.findAll();
		consultar();
	}

	private void obterAtendimentos() {
		if ( this.pacienteSelecionado != null ) {
			this.listaAtendimentos = this.atendimentoService.findAllAtendimentosByPaciente( this.pacienteSelecionado.getId() );
		}
	}

	public void limparEdit() {
		inicializar();
	}
	
    private void limpar() {
    	this.pacienteSelecionado = null;
    	this.nomePaciente = null;
    	this.atendimentos = null;
    	this.listaAtendimentos = null;
    }

	public void consultar() {
		try {
			this.atendimentoService.findAllByNomePaciente( this.nomePaciente );
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao consultar os pacientes. ", null ) );
		}
	}

	public List<String> completePaciente( String query ) {
		List<String> results = new ArrayList<String>();
		if ( query != null && this.listaCliente != null && !this.listaCliente.isEmpty() ) {
			for ( Cliente a: this.listaCliente ) {
				if ( a.getNome().toUpperCase().indexOf( query.toUpperCase() ) >= 0 ) {
					results.add( a.getNome() );
				}
			}
		}
		return results;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes( List<Cliente> clientesParam ) {
		clientes = clientesParam;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente( Cliente clienteParam ) {
		cliente = clienteParam;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual( Date dataAtualParam ) {
		dataAtual = dataAtualParam;
	}

	public List<Cliente> getListaCliente() {
		return this.listaCliente;
	}

	public void setListaCliente( List<Cliente> listaClienteParam ) {
		this.listaCliente = listaClienteParam;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente( String nomePacienteParam ) {
		this.nomePaciente = nomePacienteParam;
	}

	public List<AtendimentoDTO> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos( List<AtendimentoDTO> atendimentosParam ) {
		this.atendimentos = atendimentosParam;
	}

	public String getTituloTela() {
		return this.tituloTela;
	}

	public void setTituloTela( String tituloTelaParam ) {
		this.tituloTela = tituloTelaParam;
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public void setClienteService( ClienteService clienteServiceParam ) {
		this.clienteService = clienteServiceParam;
	}

	public boolean isChamadaDePaciente() {
		return this.chamadaDePaciente;
	}

	public void setChamadaDePaciente( boolean chamadaDePacienteParam ) {
		this.chamadaDePaciente = chamadaDePacienteParam;
	}

	public Cliente getPacienteSelecionado() {
		return this.pacienteSelecionado;
	}

	public void setPacienteSelecionado( Cliente pacienteSelecionadoParam ) {
		this.pacienteSelecionado = pacienteSelecionadoParam;
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente( Long idClienteParam ) {
		this.idCliente = idClienteParam;
	}

	public List<Atendimento> getListaAtendimentos() {
		return this.listaAtendimentos;
	}

	public void setListaAtendimentos( List<Atendimento> listaAtendimentosParam ) {
		this.listaAtendimentos = listaAtendimentosParam;
	}
}
