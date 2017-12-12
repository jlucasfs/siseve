package br.com.mobiew.siseve.model.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.dto.RelatorioDto;
import br.com.mobiew.siseve.model.dao.AtendimentoDAO;
import br.com.mobiew.siseve.model.dao.base.GenericHibernateDAO;
import br.com.mobiew.siseve.model.entity.Atendimento;
import br.com.mobiew.siseve.model.entity.Evento;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public class AtendimentoDAOImpl extends GenericHibernateDAO<Atendimento, Long> implements AtendimentoDAO {

    @Autowired
    public AtendimentoDAOImpl( HibernateTemplate hibernateTemplateParam ) {
        setHibernateTemplate( hibernateTemplateParam );
    }

	@SuppressWarnings( "unchecked" )
	@Override
	public List<RelatorioDto> findAllRelatorio( Evento eventoParam ) {
		List<RelatorioDto> result = null;
        final StringBuilder sql = new StringBuilder();
        sql.append( "SELECT cli.nome nome, " ); 
        sql.append( "		YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(cli.data_nascimento))) idade, " ); 
        sql.append( "       cli.endereco endereco, " ); 
        sql.append( "		cli.email email, " ); 
        sql.append( "		cli.telefone telefone, " ); 
        sql.append( "		eve.local local, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'ODONTO%', 1, 0)) qtdeOdontologia, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'PEDIAT%', 1, 0)) qtdePediatria, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'CLINIC%', 1, 0)) qtdeClinicAGeral, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'GINECO%', 1, 0)) qtdeGinecologia, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'FISIOT%', 1, 0)) qtdeFisioterapia, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'CORTE%', 1, 0)) qtdeCorteCabelo, " ); 
        sql.append( " 		SUM(IF(ser.nome LIKE 'JURIDI%', 1, 0)) qtdeAdvogado " ); 
        sql.append( "FROM atendimento ate " ); 
        sql.append( "INNER JOIN cliente cli ON cli.id = ate.id_cliente " ); 
        sql.append( "INNER JOIN servico ser ON ser.id = ate.id_servico " ); 
        sql.append( "INNER JOIN evento eve ON eve.id = ser.id_evento " ); 
        sql.append( "WHERE ser.id_evento = :idevento " );
        sql.append( " GROUP BY cli.nome , " ); 
        sql.append( " 		YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(cli.data_nascimento))) , " ); 
        sql.append( "         cli.endereco , " ); 
        sql.append( " 		cli.email , " ); 
        sql.append( " 		cli.telefone  " ); 
        final SQLQuery query = getSession().createSQLQuery( sql.toString() );
        query.setLong( "idevento", eventoParam.getId() );
        try {
            query.addScalar( "nome", StringType.class.newInstance() );
            query.addScalar( "idade", LongType.class.newInstance() );
            query.addScalar( "endereco", StringType.class.newInstance() );
            query.addScalar( "telefone", StringType.class.newInstance() );
            query.addScalar( "email", StringType.class.newInstance() );
            query.addScalar( "local", StringType.class.newInstance() );
            query.addScalar( "qtdeOdontologia", LongType.class.newInstance() );
            query.addScalar( "qtdePediatria", LongType.class.newInstance() );
            query.addScalar( "qtdeClinicaGeral", LongType.class.newInstance() );
            query.addScalar( "qtdeGinecologia", LongType.class.newInstance() );
            query.addScalar( "qtdeFisioterapia", LongType.class.newInstance() );
            query.addScalar( "qtdeCorteCabelo", LongType.class.newInstance() );
            query.addScalar( "qtdeAdvogado", LongType.class.newInstance() );
            query.setResultTransformer( Transformers.aliasToBean( RelatorioDto.class ) );
            result = query.list();
        } catch ( final Exception ex ) {
            ex.printStackTrace();
        }
        return result;
	}

}
