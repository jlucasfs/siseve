package br.com.mobiew.siseve.service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobiew.siseve.model.dao.UsuarioDAO;
import br.com.mobiew.siseve.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioServiceImpl( final UsuarioDAO usuarioDAOParam ) {
        this.usuarioDAO =  usuarioDAOParam ;
    }

    @Override
    public Usuario findById( Long idParam ) {
        return this.usuarioDAO.findById( idParam );
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarioDAO.findAll();
    }

    @Override
    public List<Usuario> findAllOrdered(String propertyName, Boolean asc) {
    	return this.usuarioDAO.findAllOrdered(propertyName, asc);
    }
    
    @Override
    public List<Usuario> findAllByProperty( String propertyNameParam, Object valueParam ) {
        return this.usuarioDAO.findAllByProperty( propertyNameParam, valueParam );
    }

    @Override
    public Usuario save( Usuario entityParam ) {
        return this.usuarioDAO.save( entityParam );
    }

    @Override
    public Usuario persist( Usuario entityParam ) {
        return null;
    }

    @Override
    public Usuario merge( Usuario mergeParam ) {
        return null;
    }

    @Override
    public void initialize( Object proxyParam ) {
        //
    }

    @Override
    public void delete( Usuario entityParam ) {
        this.usuarioDAO.delete( entityParam );
    }

    @Override
    public List<Usuario> findByCriteria( DetachedCriteria detachedCriteriaParam ) {
        return this.usuarioDAO.findByCriteria( detachedCriteriaParam );
    }

    @Override
    public Usuario findByLoginSenha( final String loginParam, final String senhaParam ) {
        Usuario usuario = this.usuarioDAO.findByLoginSenha( loginParam, senhaParam );
        return usuario;
    }

    @Override
    public Set<Usuario> findByCodigoPerfil( String codigoPerfilParam ) {
        return this.usuarioDAO.findByCodigoPerfil( codigoPerfilParam );
    }

}
