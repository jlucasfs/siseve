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

import br.com.mobiew.siseve.model.dao.ServicoDao;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.model.entity.Servico;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class ServicoDaoImpl extends GenericHibernateDAO<Servico, Long> implements ServicoDao {

    private static final Logger LOG = Logger.getLogger( ServicoDaoImpl.class );

    @Autowired
    public ServicoDaoImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Servico> findAll( String nomeParam ) {
    	List<Servico>  result = null;
    	try {
        	DetachedCriteria criteria = DetachedCriteria.forClass( Profissional.class, "a" );
        	criteria.createCriteria( "a.pessoa", "p", Criteria.INNER_JOIN );
        	criteria.createCriteria( "p.pessoaFisica", "pf", Criteria.INNER_JOIN );
        	if ( StringUtils.isNotBlank( nomeParam ) ) {
        		criteria.add( Restrictions.ilike( "p.nome", nomeParam.trim(), MatchMode.ANYWHERE ) );
        	}
        	criteria.addOrder(Order.asc("p.nome"));
        	criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        	result = getHibernateTemplate().findByCriteria( criteria );
        } catch ( Exception e ) {
        	LOG.error( "Erro ao acessar lista de profissionais - " + e.getMessage() );
        }
    	return result;
    }

}
