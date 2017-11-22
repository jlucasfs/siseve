package br.com.mobiew.siseve.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.entity.Cliente;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.model.enuns.UFEnum;
import br.com.mobiew.siseve.service.ClienteService;
import br.com.mobiew.siseve.service.EventoService;
import br.com.mobiew.siseve.util.Constantes;
import br.com.mobiew.siseve.util.MessagesHelper;
import br.com.mobiew.siseve.util.Util;
import br.com.mobiew.siseve.util.scopes.Scopes;

@Controller
@Scope( Scopes.SESSION )
public class ClienteController {

	private static final Logger LOG = Logger.getLogger( ClienteController.class );

	private String entidade = "Paciente";

	@Autowired
	private ClienteService clienteService;

	private List<Cliente> clientes;

	private List<PacienteDTO> listaCliente;

	private List<PacienteDTO> listaPacienteDto;

	private Cliente cliente = new Cliente();

	private Cliente clienteAtender;

	private String nome;

	private String cpf;

	private String cnpj;

	private Date dataAtual = new Date();

	private String tipoPessoa = "T";

	@Autowired
	private EventoService eventoService;

	private List<Evento> eventosSelecionados;

	private List<Evento> eventos;

	private Long idCliente;

	@Autowired
	private MessagesHelper messages;

	private List<Cliente> pacientes;

	private PacienteDTO pacienteSelecionado;

	private int paginaAtual;

	private Date dataMinima;

	private String sexo;

	private Integer idadeInicial;

	private Integer idadeFinal;

	@PostConstruct
	public void inicializar() {
		this.cliente = new Cliente();
		this.nome = null;
		this.cpf = null;
		this.cnpj = null;
		this.tipoPessoa = "T";
		this.pacienteSelecionado = null;
		consultar();
		this.eventos = eventoService.findAll();
		this.eventosSelecionados = new ArrayList<Evento>();
		this.dataMinima = new DateTime().withDate( 1900, 1, 1 ).toDate();
	}

	public String entrar() {
		inicializar();
		return "/pages/paciente-index?faces-redirect=true";
	}

	public String voltar() {
		return "/pages/paciente-index?faces-redirect=true";
	}

	public void limparEdit() {
		inicializar();
	}

	public void consultar() {
		try {
			this.pacientes = this.clienteService.findAll( nome, sexo, idadeInicial, idadeFinal );
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao consultar os pacientes. ", null ) );
		}
	}

	public List<PacienteDTO> completePaciente( String query ) {
		List<PacienteDTO> results = new ArrayList<PacienteDTO>();
		this.listaCliente = this.clienteService.findAllPacientes();
		if ( query != null && this.listaCliente != null && !this.listaCliente.isEmpty() ) {
			for ( PacienteDTO item: this.listaCliente ) {
				String nome1 = Util.convertToNormalize( item.getNome().toUpperCase( Constantes.LOCALE_PT_BR ) );
				String nome2 = Util.convertToNormalize( query.toUpperCase( Constantes.LOCALE_PT_BR ) );
				if ( nome1.indexOf( nome2 ) >= 0 ) {
					results.add( item );
				}
			}
		}
		return results;
	}

	public List<String> completeNomePaciente( String query ) {
		List<String> results = new ArrayList<String>();
		this.listaCliente = this.clienteService.findAllPacientes();
		if ( query != null && this.listaCliente != null && !this.listaCliente.isEmpty() ) {
			for ( PacienteDTO a: this.listaCliente ) {
				String nome1 = Util.convertToNormalize( a.getNome().toUpperCase( Constantes.LOCALE_PT_BR ) );
				String nome2 = Util.convertToNormalize( query.toUpperCase( Constantes.LOCALE_PT_BR ) );
				if ( nome1.indexOf( nome2 ) >= 0 ) {
					results.add( a.getNome() );
				}
			}
		}
		return results;
	}

	public String incluir() {
		this.cliente = new Cliente();
		this.tipoPessoa = "F";
		this.idCliente = null;
		this.eventosSelecionados = new ArrayList<Evento>();
		return editar();
	}

	public String editar() {
		if ( this.idCliente != null ) {
			this.cliente = this.clienteService.findById( this.idCliente );
			this.eventosSelecionados = new ArrayList<Evento>();
			// for (PacienteEvento pacEvento : this.cliente.getPacienteEventos()) {
			// if ( pacEvento.getEvento().getDtEvento() != null ) {
			// this.eventosSelecionados.add( pacEvento.getEvento() );
			// }
			// }
		}

		return "paciente-edit?faces-redirect=true";
	}

	public String salvar() {
		try {
			if ( StringUtils.isBlank( this.cliente.getNome() ) ) {
				ControllerUtil.addErrorMessage( null, "Campo Nome é obrigatório." );
				return null;
			}
			boolean inclusao = this.cliente.getId() == null;
			this.clienteService.save( this.cliente );
			ControllerUtil.addInfoMessage( null, "Paciente " + ( inclusao ? "incluído" : "alterado" ) + " com sucesso." );
			inicializar();
			return "paciente-index?faces-redirect=true";
		} catch ( Exception e ) {
			ControllerUtil.addErrorMessage( null, "Erro ao salvar " + this.entidade + "." );
			e.getMessage();
			return null;
		}
	}

	public void exclusao() {
		this.cliente = this.clienteService.findById( this.idCliente );
	}

	public void excluir() {
		try {
			this.clienteService.delete( this.cliente );
			inicializar();
			ControllerUtil.addInfoMessage( null, "Paciente excluído com sucesso." );
		} catch ( Exception e ) {
			e.getMessage();
			ControllerUtil.addErrorMessage( null, "Erro ao excluir " + this.entidade + "." );
		}
	}

	public static List<UFEnum> getAllUFs() {
		return Arrays.asList( UFEnum.values() );
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade( String entidadeParam ) {
		entidade = entidadeParam;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes( List<Cliente> clientesParam ) {
		clientes = clientesParam;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nomeParam ) {
		nome = nomeParam;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpfParam ) {
		cpf = cpfParam;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente( Cliente clienteParam ) {
		cliente = clienteParam;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj( String cnpjParam ) {
		cnpj = cnpjParam;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual( Date dataAtualParam ) {
		dataAtual = dataAtualParam;
	}

	public String getTipoPessoa() {
		return this.tipoPessoa;
	}

	public void setTipoPessoa( String tipoPessoaParam ) {
		this.tipoPessoa = tipoPessoaParam;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos( List<Evento> eventosParam ) {
		this.eventos = eventosParam;
	}

	public List<Evento> getEventosSelecionados() {
		return this.eventosSelecionados;
	}

	public void setEventosSelecionados( List<Evento> eventosSelecionadosParam ) {
		this.eventosSelecionados = eventosSelecionadosParam;
	}

	public Cliente getClienteAtender() {
		return this.clienteAtender;
	}

	public void setClienteAtender( Cliente clienteAtenderParam ) {
		this.clienteAtender = clienteAtenderParam;
	}

	public List<PacienteDTO> getListaPacienteDto() {
		return this.listaPacienteDto;
	}

	public void setListaPacienteDto( List<PacienteDTO> listaPacienteDtoParam ) {
		this.listaPacienteDto = listaPacienteDtoParam;
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente( Long idClienteParam ) {
		this.idCliente = idClienteParam;
	}

	public List<PacienteDTO> getListaCliente() {
		return this.listaCliente;
	}

	public void setListaCliente( List<PacienteDTO> listaClienteParam ) {
		this.listaCliente = listaClienteParam;
	}

	public PacienteDTO getPacienteSelecionado() {
		return this.pacienteSelecionado;
	}

	public void setPacienteSelecionado( PacienteDTO pacienteSelecionadoParam ) {
		this.pacienteSelecionado = pacienteSelecionadoParam;
	}

	public MessagesHelper getMessages() {
		return this.messages;
	}

	public void setMessages( MessagesHelper messagesParam ) {
		this.messages = messagesParam;
	}

	public int getPaginaAtual() {
		return this.paginaAtual;
	}

	public void setPaginaAtual( int paginaAtualParam ) {
		this.paginaAtual = paginaAtualParam;
	}

	public Date getDataMinima() {
		return this.dataMinima;
	}

	public void setDataMinima( Date dataMinimaParam ) {
		this.dataMinima = dataMinimaParam;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo( String sexoParam ) {
		this.sexo = sexoParam;
	}

	public Integer getIdadeInicial() {
		return this.idadeInicial;
	}

	public void setIdadeInicial( Integer idadeInicialParam ) {
		this.idadeInicial = idadeInicialParam;
	}

	public Integer getIdadeFinal() {
		return this.idadeFinal;
	}

	public void setIdadeFinal( Integer idadeFinalParam ) {
		this.idadeFinal = idadeFinalParam;
	}

	public List<Cliente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes( List<Cliente> pacientesParam ) {
		this.pacientes = pacientesParam;
	}
}
