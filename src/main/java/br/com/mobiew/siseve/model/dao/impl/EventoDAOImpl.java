package br.com.mobiew.siseve.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
		DetachedCriteria criteria = DetachedCriteria.forClass( Evento.class, "evt" );
        return getHibernateTemplate().findByCriteria( criteria );
	}	

	@Override
	public List<Evento> find(String descricao, Date dtEvento) {
		DetachedCriteria dc = DetachedCriteria.forClass( Evento.class, "ev" );
		if( StringUtils.isNotBlank(descricao) ) {
			dc.add(Restrictions.ilike( "ev.descricao", descricao, MatchMode.ANYWHERE ) );
		}
		if( dtEvento != null ) {
			dc.add(Restrictions.eq( "ev.dtEvento", dtEvento ) );
		}
		dc.addOrder( Order.asc("ev.descricao"));

		List<Evento> eventos = this.findByCriteria( dc );

		return eventos;
	}
}
