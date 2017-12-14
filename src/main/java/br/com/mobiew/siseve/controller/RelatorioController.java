package br.com.mobiew.siseve.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.dto.RelatorioDto;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.service.AtendimentoService;
import br.com.mobiew.siseve.service.EventoService;
import br.com.mobiew.siseve.util.Constantes;
import br.com.mobiew.siseve.util.MessagesHelper;
import br.com.mobiew.siseve.util.Util;
import br.com.mobiew.siseve.util.scopes.Scopes;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@Scope( Scopes.SESSION )
public class RelatorioController {

	private static final Logger LOG = Logger.getLogger( RelatorioController.class );

	@Autowired
	private EventoService eventoService;

	@Autowired
	private AtendimentoService atendimentoService;

	private List<Evento> eventosSelecionados;

	private List<Evento> eventos;

	@Autowired
	private MessagesHelper messages;

	private Evento eventoAtual;

	private Long idEvento;

	@PostConstruct
	public void inicializar() {
		this.eventos = eventoService.findAll();
		this.eventosSelecionados = new ArrayList<Evento>();
		this.eventoAtual = this.eventoService.findEventoAtual();
		if ( this.eventoAtual != null ) {
			this.idEvento = this.eventoAtual.getId();
		}
	}

	public String entrar() {
		inicializar();
		return "/pages/relatorio?faces-redirect=true";
	}

	public void imprimirRelatorioXls() {
		try {
			Evento evento = this.eventoAtual;
			if ( this.idEvento != null ) {
				evento = this.eventoService.findById( this.idEvento );
			}
			List<RelatorioDto> lista = this.atendimentoService.findAllRelatorio( evento );
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put( Constantes.PARAMETRO_REPORT_LOCALE, Constantes.LOCALE_PT_BR );
			parameters.put( JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE );
			InputStream arquivoJasperIS = getClass().getClassLoader().getResourceAsStream( "report/relatorioXls.jasper" );
			JasperPrint print = JasperFillManager.fillReport( arquivoJasperIS, parameters, new JRBeanCollectionDataSource( lista ) );
			Util.gerarRelatorioXls( (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), print, "relatorio.xls" );
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( FacesMessage.FACES_MESSAGES,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao gerar relatorio geral", e.getMessage() ) );
		}
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

	public MessagesHelper getMessages() {
		return this.messages;
	}

	public void setMessages( MessagesHelper messagesParam ) {
		this.messages = messagesParam;
	}

	public Evento getEventoAtual() {
		return this.eventoAtual;
	}

	public void setEventoAtual( Evento eventoAtualParam ) {
		this.eventoAtual = eventoAtualParam;
	}

	public Long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento( Long idEventoParam ) {
		this.idEvento = idEventoParam;
	}
}
