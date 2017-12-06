package br.com.mobiew.siseve.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.service.EventoService;
import br.com.mobiew.siseve.util.scopes.Scopes;

@Controller
@Scope( Scopes.SESSION )
public class EventoController {

	private static final Logger LOG = Logger.getLogger( EventoController.class );

	@Autowired
	private EventoService eventoService;

	private String descricao;

	private Evento eventoSelecionado;

	private Evento evento;

	private List<Evento> eventos;

	private Long idEvento;

	private String local;
	
	private boolean copiaServicos;

	@PostConstruct
	public void inicializar() {
		try {
			limpar();
			consultar();
		} catch ( Exception e ) {
			ControllerUtil.addWarnMessage( null, "Erro ao carregar Eventos" );
		}
	}

	public String entrar() {
		inicializar();
		return "/pages/evento-index?faces-redirect=true";
	}

	public void limpar() {
		this.evento = new Evento();
		this.descricao = null;
		this.local = null;
	}

	public void consultar() {
		try {
			this.eventos = eventoService.findAll( descricao );
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao consultar os eventos. ", null ) );
		}
	}

	public String incluir() {
		this.evento = new Evento();
		this.idEvento = null;
		this.copiaServicos = false;
		return "evento-edit?faces-redirect=true";
	}

	public String editar() {
		this.copiaServicos = this.evento.getServicos().isEmpty();
		return "evento-edit?faces-redirect=true";
	}

	public String salvar() {
		try {
			if ( StringUtils.isBlank( this.evento.getNome() ) ) {
				ControllerUtil.addErrorMessage( null, "Campo Nome é obrigatório." );
				return null;
			}
			boolean inclusao = this.evento.getId() == null;
			this.eventoService.save( this.evento );
			ControllerUtil.addInfoMessage( null, "Evento " + ( inclusao ? "incluído" : "alterado" ) + " com sucesso." );
			inicializar();
			return "evento-index?faces-redirect=true";
		} catch ( Exception e ) {
			ControllerUtil.addErrorMessage( null, "Erro ao salvar o Evento." );
			e.getMessage();
			return null;
		}
	}
	
	public String copiar() {
		try {
			Evento eventoCopia = this.eventoService.copiarServicos( this.evento );
			if ( eventoCopia == null ) {
				ControllerUtil.addWarnMessage( null, "Não existe Evento anterior ou não há serviços a copiar." );
				return "evento-index?faces-redirect=true";
			}
			ControllerUtil.addInfoMessage( null, "Serviços do evento anterior copiados com sucesso." );
			inicializar();
			return "evento-index?faces-redirect=true";
		} catch ( Exception e ) {
			ControllerUtil.addErrorMessage( null, "Erro ao copiar os serviços do evento anterior." );
			e.getMessage();
			return null;
		}
	}

	public void excluir() {
		try {
			this.eventoService.delete( this.evento );
			inicializar();
			ControllerUtil.addInfoMessage( null, "Evento excluído com sucesso." );
		} catch ( Exception e ) {
			e.getMessage();
			ControllerUtil.addErrorMessage( null, "Erro ao excluir o Evento." );
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado( Evento eventoSelecionado ) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento( Evento evento ) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos( List<Evento> eventos ) {
		this.eventos = eventos;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento( Long idEvento ) {
		this.idEvento = idEvento;
	}

	public Long getTotalRegistros() {
		this.eventos = this.eventoService.findAll();
		return Long.valueOf( this.eventos.size() );
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal( String localParam ) {
		this.local = localParam;
	}

	public boolean isCopiaServicos() {
		return this.copiaServicos;
	}

	public void setCopiaServicos( boolean copiaServicosParam ) {
		this.copiaServicos = copiaServicosParam;
	}

}
