package br.com.mobiew.siseve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.EventoDAO;
import br.com.mobiew.siseve.model.entity.Evento;

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
		return this.eventoDAO.findAll();
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
		return this.eventoDAO.findAllByProperty( "nome", nomeParam );
	}

}
