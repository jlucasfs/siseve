package br.com.mobiew.siseve.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.model.entity.Usuario;
import br.com.mobiew.siseve.model.enuns.UFEnum;
import br.com.mobiew.siseve.service.ProfissionalService;
import br.com.mobiew.siseve.service.UsuarioService;
import br.com.mobiew.siseve.util.Constantes;
import br.com.mobiew.siseve.util.Util;
import br.com.mobiew.siseve.util.scopes.Scopes;

@Controller
@Scope( Scopes.SESSION )
public class ProfissionalController {

    private static final Logger LOG = Logger.getLogger( ProfissionalController.class );

    private String entidade = "Profissional";

    @Autowired
    private ProfissionalService service;

    private List<Profissional> entities;

    private List<Profissional> listaEntity;

    private Profissional entity = new Profissional();

    private String nome;

    private String cpf;

    private Date dataAtual = new Date();

    private String tipoPessoa = "T";

    @Autowired
    private UsuarioService usuarioService;

    private Long idUsuarioSelecionado;

    private List<Usuario> usuarios;

    @PostConstruct
    public void inicializar() {
        this.entity = new Profissional();
        this.nome = null;
        this.cpf = null;
        this.tipoPessoa = "T";
        this.listaEntity = this.service.findAll();
        this.usuarios = this.usuarioService.findAll();
        this.idUsuarioSelecionado = null;
        consultar();
    }

    public String entrar() {
        inicializar();
        return "profissional-index?faces-redirect=true";
    }

    public void limparEdit() {
        inicializar();
    }

    public void consultar() {
        try {
            this.entities = this.service.findAll( this.nome, this.cpf );
            Collections.sort( this.entities );
        } catch ( Exception e ) {
            LOG.error( e.getMessage() );
            ControllerUtil.addErrorMessage( null, "Erro ao consultar os profissionais." );
        }
    }

    public List<Profissional> completeProfissional( String query ) {
        List<Profissional> results = new ArrayList<Profissional>();
        if ( query != null && this.listaEntity != null && !this.listaEntity.isEmpty() ) {
            for ( Profissional item: this.listaEntity ) {
                if ( item.getNome().toUpperCase().indexOf( query.toUpperCase() ) >= 0 ) {
                    results.add( item );
                }
            }
        }
        return results;
    }

    public List<String> completeEntity( String query ) {
        List<String> results = new ArrayList<String>();
        if ( query != null && this.listaEntity != null && !this.listaEntity.isEmpty() ) {
            for ( Profissional a: this.listaEntity ) {
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
        this.entity = new Profissional();
        this.tipoPessoa = "F";
        return editar();
    }

    public String editar() {
        return "profissional-edit?faces-redirect=true";
    }

    public String salvar() {
        try {
            this.service.save( this.entity );
            ControllerUtil.addInfoMessage( null, this.entidade + " atualizado com sucesso." );
            inicializar();
            return "profissional-index?faces-redirect=true";
        } catch ( Exception e ) {
            ControllerUtil.addErrorMessage( null, "Erro ao salvar " + this.entidade + "." );
            return null;
        }
    }

    public void excluir() {
        try {
            this.service.delete( this.entity );
            inicializar();
            ControllerUtil.addInfoMessage( null, this.entidade + " excluído com sucesso." );
        } catch ( Exception e ) {
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

    public List<Profissional> getEntities() {
        return this.entities;
    }

    public void setEntities( List<Profissional> entitiesParam ) {
        this.entities = entitiesParam;
    }

    public List<Profissional> getListaEntity() {
        return this.listaEntity;
    }

    public void setListaEntity( List<Profissional> listaEntityParam ) {
        this.listaEntity = listaEntityParam;
    }

    public Profissional getEntity() {
        return this.entity;
    }

    public void setEntity( Profissional entityParam ) {
        this.entity = entityParam;
    }

    public Long getTotalRegistros() {
        if ( CollectionUtils.isEmpty( this.entities ) ) {
            this.entities = this.service.findAll();
        }
        return Long.valueOf( this.entities.size() );
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios( List<Usuario> usuariosParam ) {
        this.usuarios = usuariosParam;
    }

    public Long getIdUsuarioSelecionado() {
        return this.idUsuarioSelecionado;
    }

    public void setIdUsuarioSelecionado( Long idUsuarioSelecionadoParam ) {
        this.idUsuarioSelecionado = idUsuarioSelecionadoParam;
    }
}
