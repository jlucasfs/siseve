package br.com.mobiew.siseve.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.dto.AtendimentoDTO;
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
        sql.append( "       cli.bairro bairro, " ); 
        sql.append( "		cli.email email, " ); 
        sql.append( "		cli.telefone telefone, " ); 
        sql.append( "		eve.local local, " ); 
        sql.append( "		eve.data_inicio dataEvento, " ); 
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
            query.addScalar( "bairro", StringType.class.newInstance() );
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
            query.addScalar( "dataEvento", DateType.class.newInstance() );
            query.setResultTransformer( Transformers.aliasToBean( RelatorioDto.class ) );
            result = query.list();
        } catch ( final Exception ex ) {
            ex.printStackTrace();
        }
        return result;
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List<AtendimentoDTO> findAllAtendimentosSeisMeses() {
		List<AtendimentoDTO> result = new ArrayList<AtendimentoDTO>();

		final StringBuilder sql = new StringBuilder();
		sql.append( " SELECT  DATE(CONCAT(mesReferencia,'01')) dataAtendimento, sum(quantidade) quantidade " );
		sql.append( " FROM (  " );
		sql.append(	"   SELECT DATE_FORMAT(eve.data_inicio,'%Y%m') mesReferencia, COUNT(distinct ate.id_cliente) quantidade " );
		sql.append( "   FROM evento eve " );
		sql.append( "   INNER JOIN servico ser ON ser.id_evento = eve.id " );
		sql.append( "   INNER JOIN atendimento ate ON ate.id_servico = ser.id " );
		sql.append( "   INNER JOIN cliente cli on cli.id = ate.id_cliente " );
		sql.append( "   WHERE eve.data_inicio <= CURDATE() " );
		sql.append( "   AND DATE_FORMAT(eve.data_inicio,'%Y%m') >= DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 5 MONTH),'%Y%m') " );
		sql.append( "   GROUP BY DATE_FORMAT(eve.data_inicio,'%Y%m') " );
		sql.append( "   union " );
		sql.append( "   select date_format(CURDATE(),'%Y%m') mesReferencia, 0 quantidade " );
		sql.append( "   union " );
		sql.append( "   select date_format(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y%m') mesReferencia, 0 quantidade " );
		sql.append( "   union " );
		sql.append( "   select date_format(DATE_SUB(CURDATE(), INTERVAL 2 MONTH),'%Y%m') mesReferencia, 0 quantidade " );
		sql.append( "   union " );
		sql.append( "   select date_format(DATE_SUB(CURDATE(), INTERVAL 3 MONTH),'%Y%m') mesReferencia, 0 quantidade " );
		sql.append( "   union " );
		sql.append( "   select date_format(DATE_SUB(CURDATE(), INTERVAL 4 MONTH),'%Y%m') mesReferencia, 0 quantidade " );
		sql.append( "   union " );
		sql.append( "   select date_format(DATE_SUB(CURDATE(), INTERVAL 5 MONTH),'%Y%m') mesReferencia, 0 quantidade " );
		sql.append( " ) tab1 " );
		sql.append( " group by mesReferencia " );
		sql.append( " order by mesReferencia " );

		final SQLQuery query = getSession().createSQLQuery( sql.toString() );
		try {
			query.addScalar( "dataAtendimento", DateType.class.newInstance() );
			query.addScalar( "quantidade", IntegerType.class.newInstance() );
			query.setResultTransformer( Transformers.aliasToBean( AtendimentoDTO.class ) );
			result = query.list();
		} catch ( final Exception ex ) {
			ex.printStackTrace();
		}
		return result;
	}

}
