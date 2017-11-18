package br.com.mobiew.siseve.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
    public List<Cliente> findAll( String nomeParam, String cpfParam, String tipoPessoaParam ) {
        DetachedCriteria criteria = DetachedCriteria.forClass( Cliente.class, "c" );
        criteria.createCriteria( "c.pessoa", "p", Criteria.INNER_JOIN );
        criteria.createCriteria( "p.pessoaFisica", "pf", Criteria.LEFT_JOIN );
        criteria.createCriteria( "p.pessoaJuridica", "pj", Criteria.LEFT_JOIN );
        criteria.createCriteria( "c.pacienteDocumentos", Criteria.LEFT_JOIN );
        if ( StringUtils.isNotBlank( cpfParam ) ) {
            if ( "T".equals( tipoPessoaParam ) ) {
                criteria.add( Restrictions.or( Restrictions.eq( "pf.cpf", cpfParam ), Restrictions.eq( "pj.cnpj", cpfParam ) ) );
            } else if ( "F".equals( tipoPessoaParam ) ) {
                criteria.add( Restrictions.eq( "pf.cpf", cpfParam ) );
            } else {
                criteria.add( Restrictions.eq( "pj.cnpj", cpfParam ) );
            }
        }
        if ( "F".equals( tipoPessoaParam ) ) {
            criteria.add( Restrictions.sqlRestriction( "idPessoa_Juridica is NULL" ) );
        } else if ( "J".equals( tipoPessoaParam ) ) {
            criteria.add( Restrictions.sqlRestriction( "idPessoa_Fisica is NULL" ) );
        }
        if ( StringUtils.isNotBlank( nomeParam ) ) {
            criteria.add( Restrictions.ilike( "p.nome", nomeParam, MatchMode.ANYWHERE ) );
        }
        criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
        return getHibernateTemplate().findByCriteria( criteria );
    }
    
    public PacienteDTO findPacientesConveniados() {
        PacienteDTO result = null;
        final StringBuilder sql = new StringBuilder();
        sql.append( " select count(1) totalConvenio from cliente where id_convenio is not null " );
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        try {
            query.addScalar( "totalConvenio", LongType.class.newInstance() );
            query.setResultTransformer( Transformers.aliasToBean( PacienteDTO.class ) );
            result = (PacienteDTO) query.uniqueResult();
        } catch ( final Exception ex ) {
            ex.printStackTrace();
        }
        return result;
    }

    public PacienteDTO findPacientesPrivados() {
        PacienteDTO result = null;
        final StringBuilder sql = new StringBuilder();
        sql.append( " select count(1) totalPrivado from cliente where id_convenio is null " );
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        try {
            query.addScalar( "totalPrivado", LongType.class.newInstance() );
            query.setResultTransformer( Transformers.aliasToBean( PacienteDTO.class ) );
            result = (PacienteDTO) query.uniqueResult();
        } catch ( final Exception ex ) {
            ex.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings( "unchecked" )
    public List<PacienteDTO> findAllPacientes( Long idParam, String cpfParam, String tipoPessoaParam, Long idConvenio, Date dataInicio, Date dataFim ) {
        List<PacienteDTO> result = null;
        final StringBuilder sql = new StringBuilder();
        sql.append( " select cli.id_cliente id, " );
        sql.append( "        pcl.nome nome,  " );
        sql.append( "        pco.nome convenio, " );
        sql.append( "        pcl.data_criacao dataInclusao, " );
        sql.append( "        cli.num_carteira numeroCarteira, " );
        sql.append( "        pfi.cpf cpf " );
        sql.append( " from cliente cli " );
        sql.append( " inner join pessoa pcl on pcl.idpessoa = cli.id_cliente " );
        sql.append( " inner join pessoa_fisica pfi on pfi.idpessoa_fisica = cli.id_cliente " );
        sql.append( " left join convenio con on con.id_convenio = cli.id_convenio " );
        sql.append( " left join pessoa pco on pco.idpessoa = con.id_convenio " );
        boolean where = false;
        if ( StringUtils.isNotBlank( cpfParam ) ) {
            sql.append( " WHERE pfi.cpf = :cpf " );
            where = true;
        }
        if ( idParam != null ) {
            if ( where ) {
                sql.append( " AND " );
            } else {
                sql.append( " WHERE " );
                where = true;
            }
            sql.append( " cli.id_cliente = :idcliente " );
        }
        if ( idConvenio != null ) {
        	if ( where ) {
        		sql.append( " AND " );
        	} else {
        		sql.append( " WHERE " );
        		where = true;
        	}
        	if ( NumberUtils.LONG_ZERO.equals( idConvenio ) ) {
        		sql.append( " cli.id_convenio IS NULL " );
        	} else {
        		sql.append( " cli.id_convenio = :idconvenio " );
        	}
        }
        if ( dataInicio != null || dataFim != null ) {
            if ( where ) {
                sql.append( " AND " );
            } else {
                sql.append( " WHERE " );
                where = true;
            }
            if ( dataInicio != null ) {
            	sql.append( " pcl.data_criacao >= :datainicio " );
            }
            if ( dataFim != null ) {
            	if ( dataInicio != null ) {
            		sql.append( " AND " );
            	}
            	sql.append( " pcl.data_criacao <= :datafim " );
            }
        }
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        try {
            if ( StringUtils.isNotBlank( cpfParam ) ) {
                query.setString( "cpf", cpfParam );
            }
            if ( idParam != null ) {
                query.setLong( "idcliente", idParam );
            }
            if ( idConvenio != null && !NumberUtils.LONG_ZERO.equals( idConvenio ) ) {
            	query.setLong( "idconvenio", idConvenio );
            }
            if ( dataInicio != null ) {
            	query.setDate( "datainicio", dataInicio );
            }
            if ( dataFim != null ) {
            	query.setDate( "datafim", dataFim );
            }
            query.addScalar( "id", LongType.class.newInstance() );
            query.addScalar( "nome", StringType.class.newInstance() );
            query.addScalar( "numeroCarteira", StringType.class.newInstance() );
            query.addScalar( "convenio", StringType.class.newInstance() );
            query.addScalar( "dataInclusao", DateType.class.newInstance() );
            query.addScalar( "cpf", StringType.class.newInstance() );
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
        sql.append( " SELECT DISTINCT p1.nome nome, end.logradouro endereco, " );
        sql.append( "                 end.numero numero, end.complemento complemento, " );
        sql.append( "                 c1.id_cliente id " );
        sql.append( " FROM cliente c1 " );
        sql.append( " INNER JOIN pessoa p1 ON p1.idpessoa = c1.id_cliente " );
        sql.append( " LEFT JOIN endereco end ON end.idpessoa = p1.idpessoa " );
        sql.append( " WHERE c1.id_cliente = " + idParam );
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        try {
            query.addScalar( "id", LongType.class.newInstance() );
            query.addScalar( "nome", StringType.class.newInstance() );
            query.addScalar( "endereco", StringType.class.newInstance() );
            query.addScalar( "numero", StringType.class.newInstance() );
            query.addScalar( "complemento", StringType.class.newInstance() );
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
