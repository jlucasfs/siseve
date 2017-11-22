package br.com.mobiew.siseve.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.model.entity.Servico;
import br.com.mobiew.siseve.service.ServicoService;
import br.com.mobiew.siseve.util.scopes.Scopes;

@Controller
@Scope( Scopes.SESSION )
public class ServicoController {

	private static final Logger LOG = Logger.getLogger( ServicoController.class );

	@Autowired
	private ServicoService servicoService;

	private String descricao;

	private Servico eventoSelecionado;

	private Servico servico;

	private List<Servico> servicos;

	private Long idServico;

	private String tipoServico;

	@PostConstruct
	public void inicializar() {
		try {
			limpar();
			this.servicos = this.servicoService.findAll();
		} catch ( Exception e ) {
			ControllerUtil.addWarnMessage( null, "Erro ao carregar Servicos" );
		}
	}

	public void limpar() {
		this.servico = new Servico();
		this.descricao = null;
	}

	public String entrar() {
		inicializar();
		return "/pages/servico-index?faces-redirect=true";
	}

	public void consultar() {
		try {
			this.servicos = servicoService.findAll( descricao );
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao consultar os eventos. ", null ) );
		}
	}

	public String incluir() {
		this.servico = new Servico();
		this.idServico = null;
		return "servico-edit?faces-redirect=true";
	}

	public String editar() {
		return "servico-edit?faces-redirect=true";
	}

	public String salvar() {
		try {
			if ( StringUtils.isBlank( this.servico.getNome() ) ) {
				ControllerUtil.addErrorMessage( null, "Campo Nome é obrigatório." );
				return null;
			}
			boolean inclusao = this.servico.getId() == null;
			this.servicoService.save( this.servico );
			ControllerUtil.addInfoMessage( null, "Servico " + ( inclusao ? "incluído" : "alterado" ) + " com sucesso." );
			inicializar();
			return "evento-index?faces-redirect=true";
		} catch ( Exception e ) {
			ControllerUtil.addErrorMessage( null, "Erro ao salvar o Servico." );
			e.getMessage();
			return null;
		}
	}

	public void excluir() {
		try {
			this.servicoService.delete( this.servico );
			inicializar();
			ControllerUtil.addInfoMessage( null, "Servico excluído com sucesso." );
		} catch ( Exception e ) {
			e.getMessage();
			ControllerUtil.addErrorMessage( null, "Erro ao excluir o Servico." );
		}
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao( String descricaoParam ) {
		this.descricao = descricaoParam;
	}

	public Servico getEventoSelecionado() {
		return this.eventoSelecionado;
	}

	public void setEventoSelecionado( Servico eventoSelecionadoParam ) {
		this.eventoSelecionado = eventoSelecionadoParam;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico( Servico servicoParam ) {
		this.servico = servicoParam;
	}

	public Long getIdServico() {
		return this.idServico;
	}

	public void setIdServico( Long idServicoParam ) {
		this.idServico = idServicoParam;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos( List<Servico> servicosParam ) {
		this.servicos = servicosParam;
	}

	public Long getTotalRegistros() {
		if ( CollectionUtils.isEmpty( this.servicos ) ) {
			this.servicos = this.servicoService.findAll();
		}
		return Long.valueOf( this.servicos.size() );
	}

	public String getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico( String tipoServicoParam ) {
		this.tipoServico = tipoServicoParam;
	}
}
