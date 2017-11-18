package br.com.mobiew.siseve.service;

import java.util.Set;

import br.com.mobiew.siseve.model.dao.UsuarioDAO;
import br.com.mobiew.siseve.model.entity.Usuario;

public interface UsuarioService extends UsuarioDAO {

    Usuario findByLoginSenha( final String login, final String senha );

    Set<Usuario> findByCodigoPerfil( String codigoPerfil );
}
