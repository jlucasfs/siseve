package br.com.mobiew.siseve.model.dao;

import java.util.Set;

import br.com.mobiew.siseve.model.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Long> {

    Usuario findByLoginSenha( final String loginParam, final String senhaParam );

    Set<Usuario> findByCodigoPerfil( String codigoPerfil );
}
