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

import br.com.mobiew.siseve.model.dao.ServicoDao;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Servico;
import br.com.mobiew.siseve.model.enuns.TipoRelatorioEnum;

@Repository
//@Transactional( propagation = Propagation.REQUIRED )
public class ServicoDaoImpl extends GenericHibernateDAO<Servico, Long> implements ServicoDao {

    private static final Logger LOG = Logger.getLogger( ServicoDaoImpl.class );

    @Autowired
    public ServicoDaoImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Servico> findAll( Long eventoParam, String nomeParam, String tipoParam ) {
    	List<Servico>  result = null;
    	try {
        	DetachedCriteria criteria = DetachedCriteria.forClass( Servico.class, "s" );
        	if ( eventoParam != null ) {
        		criteria.add( Restrictions.eq( "s.evento.id", eventoParam ) );
        	}
        	if ( StringUtils.isNotBlank( nomeParam ) ) {
        		criteria.add( Restrictions.ilike( "s.nome", nomeParam.trim(), MatchMode.ANYWHERE ) );
        	}
        	if ( StringUtils.isNotBlank( tipoParam ) && !"T".equals( tipoParam )) {
        		criteria.add( Restrictions.eq( "s.tipoRelatorio", TipoRelatorioEnum.getEnumFromValue( tipoParam ) ) );
        	}
        	criteria.addOrder( Order.asc("s.nome") );
        	criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        	result = getHibernateTemplate().findByCriteria( criteria );
        } catch ( Exception e ) {
        	LOG.error( "Erro ao acessar lista de servicos - " + e.getMessage() );
        }
    	return result;
    }

	@SuppressWarnings( "unchecked" )
	@Override
	public List<Servico> findAllEventoAtual() {
		List<Servico> result = null;
    	try {
        	DetachedCriteria criteria = DetachedCriteria.forClass( Servico.class, "s" );
			criteria.add( Restrictions.sqlRestriction( " this_.id_evento = (select id from evento ev where data_inicio = (select max(data_inicio) from evento)) " ) );
			criteria.addOrder( Order.asc( "s.nome" ) );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        	result = getHibernateTemplate().findByCriteria( criteria );
        } catch ( Exception e ) {
        	LOG.error( "Erro ao acessar lista de servicos - " + e.getMessage() );
        }
    	return result;
	}

}
