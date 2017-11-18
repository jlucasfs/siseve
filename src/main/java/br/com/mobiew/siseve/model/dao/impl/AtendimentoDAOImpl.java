package br.com.mobiew.siseve.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.AtendimentoDAO;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Atendimento;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class AtendimentoDAOImpl extends GenericHibernateDAO<Atendimento, Long> implements AtendimentoDAO {

    @Autowired
    public AtendimentoDAOImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

}
