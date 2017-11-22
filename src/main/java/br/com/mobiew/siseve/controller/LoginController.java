package br.com.mobiew.siseve.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.model.entity.Usuario;
import br.com.mobiew.siseve.service.UsuarioService;
import br.com.mobiew.siseve.util.Constantes;

@Controller
@Scope( "session" )
public class LoginController implements Serializable {

    private static final long serialVersionUID = 4041328290877899506L;

    private static final Logger LOG = Logger.getLogger( LoginController.class );

    private Usuario usuario = new Usuario();

    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    public void init() {
    	this.usuario = this.usuarioService.findByLoginSenha( "admin", "admin" );
    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
    	session.setAttribute( Constantes.USUARIO_AUTENTICADO, usuario );
    }

    /**
     * Autentica o usuario no sistema e direciona para a pagina inicial.
     * 
     * @return a String "home", no caso de sucesso de login ou "accessdenied" no caso de falha.
     */
    public String doLogin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
        session.setAttribute( Constantes.USUARIO_AUTENTICADO, null );
        Usuario usuarioLido = null;
        usuarioLido = this.usuarioService.findByLoginSenha( usuario.getCdLogin(), usuario.getCdSenha() );
        if ( usuarioLido == null ) {
            FacesContext.getCurrentInstance().addMessage( null,
                    new FacesMessage( FacesMessage.SEVERITY_ERROR, "Usuário não cadastrado ou nao possui acesso ao sistema.", null ) );
            return "login";
        }
        session.setAttribute( Constantes.USUARIO_AUTENTICADO, usuarioLido );
        LOG.warn( "usuario logado = " + usuarioLido.getCdLogin() + " - " + usuarioLido.getNmUsuario() );
        return "home?faces-redirect=true";
    }

    /**
     * Efetua o logoff e redireciona para tela de login.
     * 
     * @return a String "Logout".
     */
    public String doLogoff() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.usuario = new Usuario();
        return "/pages/login?faces-redirect=true";
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario( Usuario usuarioParam ) {
        this.usuario = usuarioParam;
    }
}
