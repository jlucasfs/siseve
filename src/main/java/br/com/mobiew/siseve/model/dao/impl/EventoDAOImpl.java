package br.com.mobiew.siseve.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import br.com.mobiew.siseve.model.dao.EventoDAO;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Evento;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class EventoDAOImpl extends GenericHibernateDAO<Evento, Long> implements EventoDAO {
	
	@Autowired
    public EventoDAOImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

	@Override
	public Evento findById(Long id) {
		return getHibernateTemplate().get(Evento.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass( Evento.class, "e" );
		criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        return getHibernateTemplate().findByCriteria( criteria );
	}	

	@Override
	public List<Evento> find( String nomeParam, Date dataParam ) {
		DetachedCriteria dc = DetachedCriteria.forClass( Evento.class, "ev" );
		if ( StringUtils.isNotBlank( nomeParam ) ) {
			dc.add( Restrictions.ilike( "ev.nome", nomeParam, MatchMode.ANYWHERE ) );
		}
		if ( dataParam != null ) {
			dc.add( Restrictions.eq( "ev.dataInicio", dataParam ) );
		}
		dc.addOrder( Order.desc( "ev.dataInicio" ) );
		dc.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
		return this.findByCriteria( dc );
	}

	@Override
	public Evento findEventoAtual() {
		Evento result = null;
		DetachedCriteria dc = DetachedCriteria.forClass( Evento.class, "e" );
		dc.addOrder( Order.desc( "e.dataInicio" ) );
		List<Evento> eventos = this.findByCriteria( dc );
		if ( eventos != null && !eventos.isEmpty() ) {
			result = eventos.get( 0 );
		}
		return result;
	}

	@Override
	public Evento findEventoAnterior( Evento eventoParam ) {
		Evento result = null;
		DetachedCriteria dc = DetachedCriteria.forClass( Evento.class, "e" );
		dc.add( Restrictions.lt( "e.dataInicio", eventoParam.getDataInicio() ) );
		dc.addOrder( Order.desc( "e.dataInicio" ) );
		dc.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
		List<Evento> eventos = this.findByCriteria( dc );
		if ( eventos != null && !eventos.isEmpty() && eventos.get( 0 ).getServicos() != null && !eventos.get( 0 ).getServicos().isEmpty() ) {
			result = eventos.get( 0 );
		}
		return result;
	}
}
