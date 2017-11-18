package br.com.mobiew.siseve.model.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.UsuarioDAO;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Usuario;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class UsuarioHibernateDAO extends GenericHibernateDAO<Usuario, Long> implements UsuarioDAO {

    @Autowired
    public UsuarioHibernateDAO( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }
    
    @SuppressWarnings( "unchecked" )
    public Usuario findByLoginSenha( final String loginParam, final String senhaParam ) {
        Usuario result = null;
        DetachedCriteria criteria = DetachedCriteria.forClass( Usuario.class, "u" )
                .add( Restrictions.eq( "u.codUsuario", loginParam ) )
                .add( Restrictions.eq( "u.senha", senhaParam ) )
                .setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        List<Usuario> usuarios = getHibernateTemplate().findByCriteria(criteria);
        if ( CollectionUtils.isNotEmpty( usuarios ) ) {
            result = usuarios.get( 0 );
        }
        return result;
    }

    @Override
    public Set<Usuario> findByCodigoPerfil( String codigoPerfil ) {
        DetachedCriteria criteria = DetachedCriteria.forClass( Usuario.class, "b" )
            .createCriteria( "perfilUsuarios", "pu" )
            .add( Restrictions.eq( "pu.codPerfilUsuario", codigoPerfil ) );
        Set<Usuario> usuarios = new HashSet<Usuario>(findByCriteria(criteria));
        return usuarios;
    }
}
