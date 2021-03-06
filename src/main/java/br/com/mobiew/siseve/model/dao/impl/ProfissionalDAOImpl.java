package br.com.mobiew.siseve.model.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.ProfissionalDAO;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.model.entity.Usuario;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class ProfissionalDAOImpl extends GenericHibernateDAO<Profissional, Long> implements ProfissionalDAO {

    private static final Logger LOG = Logger.getLogger( ProfissionalDAOImpl.class );

    @Autowired
    public ProfissionalDAOImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Profissional> findAll( String nomeParam ) {
    	List<Profissional>  result = null;
    	try {
        	DetachedCriteria criteria = DetachedCriteria.forClass( Profissional.class, "a" );
        	if ( StringUtils.isNotBlank( nomeParam ) ) {
        		criteria.add( Restrictions.ilike( "a.nome", nomeParam.trim(), MatchMode.ANYWHERE ) );
        	}
        	criteria.addOrder(Order.asc("a.nome"));
        	criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        	result = getHibernateTemplate().findByCriteria( criteria );
        } catch ( Exception e ) {
        	LOG.error( "Erro ao acessar lista de profissionais - " + e.getMessage() );
        }
    	return result;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Profissional findByUsuario(Usuario usuario) {
		Profissional ent = null;
		DetachedCriteria criteria = DetachedCriteria.forClass( Profissional.class, "d" );
		if ( usuario != null ) {
            criteria.add( Restrictions.eq( "d.usuario", usuario ) );
        }
		List<Profissional> entities = getHibernateTemplate().findByCriteria( criteria );
		
		if ( !entities.isEmpty()) {
			ent = entities.get(0);
		}
		return ent;
	}

}
