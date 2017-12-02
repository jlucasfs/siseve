package br.com.mobiew.siseve.model.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.dao.ClienteDAO;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Cliente;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class ClienteDAOImpl extends GenericHibernateDAO<Cliente, Long> implements ClienteDAO {

    @Autowired
    public ClienteDAOImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

    @SuppressWarnings( "unchecked" )
    public List<Cliente> findAll( Cliente cliente ) {
        DetachedCriteria criteria = DetachedCriteria.forClass( Cliente.class, "cli" );
        return getHibernateTemplate().findByCriteria( criteria );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<Cliente> findAll( String nome, String sexo, Integer idadeInicial, Integer idadeFinal ) {
        DetachedCriteria criteria = DetachedCriteria.forClass( Cliente.class, "c" );
        if ( StringUtils.isNotBlank( nome ) ) {
            criteria.add( Restrictions.ilike( "c.nome", nome, MatchMode.ANYWHERE ) );
        }
        if ( StringUtils.isNotBlank( sexo ) ) {
    		criteria.add( Restrictions.eq( "c.sexo", sexo ) );
    	}
    	if ( idadeInicial == null ) {
    		if ( idadeFinal != null ) {
    			criteria.add( Restrictions.le( "c.idade", idadeFinal ) );
    		}
    	} else {
    		if ( idadeFinal == null ) {
    			criteria.add( Restrictions.ge( "c.idade", idadeInicial ) );
    		} else {
    			criteria.add( Restrictions.between( "c.idade", idadeInicial, idadeFinal ) );
    		}
    	}
        criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        return getHibernateTemplate().findByCriteria( criteria );
    }
    
    @SuppressWarnings( "unchecked" )
    public List<PacienteDTO> findAllPacientes() {
        List<PacienteDTO> result = null;
        final StringBuilder sql = new StringBuilder();
        sql.append( " select cli.id id, " );
        sql.append( "        cli.nome nome,  " );
        sql.append( "        cli.sexo sexo, " );
        sql.append( "        cli.data_nascimento dataNascimento " );
        sql.append( " from cliente cli " );
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        try {
            query.addScalar( "id", LongType.class.newInstance() );
            query.addScalar( "nome", StringType.class.newInstance() );
            query.addScalar( "sexo", StringType.class.newInstance() );
            query.addScalar( "dataNascimento", DateType.class.newInstance() );
            query.setResultTransformer( Transformers.aliasToBean( PacienteDTO.class ) );
            result = query.list();
        } catch ( final Exception ex ) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @SuppressWarnings( "unchecked" )
    @Override
    public PacienteDTO findDadosGenericosById( Long idParam ) {
        PacienteDTO result = null;
        final StringBuilder sql = new StringBuilder();
        sql.append( " SELECT DISTINCT cl.nome nome, " );
        sql.append( "                 c1.id id " );
        sql.append( " FROM cliente cl " );
        sql.append( " WHERE cl.id = " + idParam );
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        try {
            query.addScalar( "id", LongType.class.newInstance() );
            query.addScalar( "nome", StringType.class.newInstance() );
            query.setResultTransformer( Transformers.aliasToBean( PacienteDTO.class ) );
            List<PacienteDTO> lista = query.list();
            if ( CollectionUtils.isNotEmpty( lista ) ) {
                result = lista.get( 0 );
            }
        } catch ( final Exception ex ) {
            ex.printStackTrace();
        }
        return result;
    }
}
