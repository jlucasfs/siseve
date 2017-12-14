package br.com.mobiew.siseve.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.EventoDAO;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.model.entity.Servico;

@Service
public class EventoServiceImpl implements EventoService {

	private EventoDAO eventoDAO;

	@Autowired
	public EventoServiceImpl( final EventoDAO paramDAO ) {
		this.eventoDAO = paramDAO;
	}

	@Override
	public Evento findById( Long id ) {
		Evento evento = this.eventoDAO.findById( id );
		return evento;
	}

	@Override
	public List<Evento> findAll() {
		List<Evento> lista = this.eventoDAO.findAll();
		if ( lista != null && !lista.isEmpty() ) {
			Collections.sort( lista, new Comparator<Evento>() {
				@Override
				public int compare( Evento o1Param, Evento o2Param ) {
					return o2Param.getDataInicio().compareTo( o1Param.getDataInicio() );
				}
			});
		}
		return lista;
	}

	@Override
	@Transactional
	public Evento save( Evento entity ) {
		return this.eventoDAO.save( entity );
	}

	@Override
	@Transactional
	public void delete( Evento entity ) {
		this.eventoDAO.delete( entity );

	}

	@Override
	public Evento persist( Evento entity ) {
		return this.eventoDAO.persist( entity );
	}

	@Override
	public Evento merge( Evento merge ) {
		return this.eventoDAO.merge( merge );
	}

	@Override
	public List<Evento> findAll( String nomeParam ) {
		List<Evento> result = null;
		result = this.eventoDAO.find( nomeParam, null );
		if ( result != null && !result.isEmpty() ) {
			for ( Evento evento: result ) {
				Hibernate.initialize( evento.getServicos() );
				for ( Servico serv: evento.getServicos() ) {
					Hibernate.initialize( serv );
				}
			}
			Collections.sort( result, new Comparator<Evento>() {
				@Override
				public int compare( Evento o1Param, Evento o2Param ) {
					return o2Param.getDataInicio().compareTo( o1Param.getDataInicio() );
				}
			});
		}
		return result;
	}

	@Override
	public Evento findEventoAtual() {
		return this.eventoDAO.findEventoAtual();
	}

	@Override
	@Transactional
	public Evento copiarServicos( Evento eventoParam ) {
		Evento result = null;
		Evento eventoAnt = this.eventoDAO.findEventoAnterior( eventoParam );
		if ( eventoAnt != null ) {
			List<Servico> listServicos = new ArrayList<>( eventoAnt.getServicos() );
			for ( Servico serv : listServicos ) {
				Servico novo = new Servico();
				novo.setAtendimentos( null );
				novo.setId( null );
				novo.setEvento( eventoParam );
				novo.setNome( serv.getNome() );
				novo.setTipoRelatorio( serv.getTipoRelatorio() );
				eventoParam.getServicos().add( novo );
			}
			result = this.eventoDAO.save( eventoParam );
		}
		return result;
	}
}
