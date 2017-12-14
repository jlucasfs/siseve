package br.com.mobiew.siseve.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.model.entity.Usuario;
import br.com.mobiew.siseve.service.AtendimentoService;
import br.com.mobiew.siseve.service.ClienteService;
import br.com.mobiew.siseve.service.EventoService;
import br.com.mobiew.siseve.service.UsuarioService;
import br.com.mobiew.siseve.util.scopes.Scopes;

/**
 * Bean com as informa&ccedil;&otilde;es mantidas durante a sess&atilde;o do usu&aacute;rio.
 */
@Component
@Scope( Scopes.SESSION )
public class SessionBean {

	private static final Logger LOG = Logger.getLogger( SessionBean.class );

	private Usuario usuarioLogado = (Usuario) Util.obterAtributoSessao( Constantes.USUARIO_AUTENTICADO );

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AtendimentoService atendimentoService;

	private PacienteDTO pacienteDto;

	private List<PacienteDTO> aniversariantesDto;

	private List<Evento> eventos;

	private CartesianChartModel listaAtendimentosSeisMeses;

	private PieChartModel listaAtendimentosPorServico;

	private String tituloSistema;

	@PostConstruct
	public void inicializar() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
			if ( session.getAttribute( Constantes.USUARIO_AUTENTICADO ) == null ) {
				Usuario usuario = this.usuarioService.findByLoginSenha( "admin", "admin" );
				session.setAttribute( Constantes.USUARIO_AUTENTICADO, usuario );
			}
			this.pacienteDto = this.clienteService.findPacientes();
			Evento eventoAtual = this.eventoService.findEventoAtual();
			if ( eventoAtual == null ) {
				this.tituloSistema = StringUtils.EMPTY;
			} else {
				this.tituloSistema = eventoAtual.getNome();
			}
			this.listaAtendimentosSeisMeses = this.atendimentoService.findAllAtendimentosSeisMeses();
			this.listaAtendimentosPorServico = this.atendimentoService.findAllAtendimentosPorServico();
		} catch ( Exception e ) {
			LOG.error( "Erro nao esperado ao inicializar o bean de escope de sessao que prepara os dados a serem exibidos na tela Home - " + e.getMessage() );
			e.printStackTrace();
		}
	}

	public String refresh() {
		inicializar();
		return "/pages/home?faces-redirect=true";
	}
	
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public void setUsuarioLogado( Usuario usuarioLogadoParam ) {
		this.usuarioLogado = usuarioLogadoParam;
	}

	public CartesianChartModel getListaProcedimentosSeisMeses() {
		return this.listaAtendimentosSeisMeses;
	}

	public void setListaProcedimentosSeisMeses( CartesianChartModel listaProcedimentosSeisMesesParam ) {
		this.listaAtendimentosSeisMeses = listaProcedimentosSeisMesesParam;
	}

	public PacienteDTO getPacienteDto() {
		return this.pacienteDto;
	}

	public void setPacienteDto( PacienteDTO pacienteDtoParam ) {
		this.pacienteDto = pacienteDtoParam;
	}

	public List<PacienteDTO> getAniversariantesDto() {
		return this.aniversariantesDto;
	}

	public void setAniversariantesDto( List<PacienteDTO> aniversariantesDtoParam ) {
		this.aniversariantesDto = aniversariantesDtoParam;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos( List<Evento> eventosParam ) {
		this.eventos = eventosParam;
	}

	public String getTituloSistema() {
		return this.tituloSistema;
	}

	public void setTituloSistema( String tituloSistemaParam ) {
		this.tituloSistema = tituloSistemaParam;
	}

	public CartesianChartModel getListaAtendimentosSeisMeses() {
		return this.listaAtendimentosSeisMeses;
	}

	public void setListaAtendimentosSeisMeses( CartesianChartModel listaAtendimentosSeisMesesParam ) {
		this.listaAtendimentosSeisMeses = listaAtendimentosSeisMesesParam;
	}

	public PieChartModel getListaAtendimentosPorServico() {
		return this.listaAtendimentosPorServico;
	}

	public void setListaAtendimentosPorServico( PieChartModel listaAtendimentosPorServicoParam ) {
		this.listaAtendimentosPorServico = listaAtendimentosPorServicoParam;
	}

}
