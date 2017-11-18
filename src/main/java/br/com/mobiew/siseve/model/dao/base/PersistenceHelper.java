package br.com.mobiew.siseve.model.dao.base;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.mobiew.siseve.util.scopes.Scopes;

@Repository( "dao" )
@Scope( Scopes.PROTOTYPE )
public class PersistenceHelper {

    @Autowired
    private SessionFactory sessionFactory;

    private HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        if ( template == null ) {
            template = new HibernateTemplate( sessionFactory );
        }

        return template;
    }
}
