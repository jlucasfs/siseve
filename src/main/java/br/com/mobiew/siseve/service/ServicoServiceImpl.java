package br.com.mobiew.siseve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.ServicoDao;
import br.com.mobiew.siseve.model.entity.Servico;

@Service
public class ServicoServiceImpl implements ServicoService {

	private ServicoDao dao;

	@Autowired
	public ServicoServiceImpl( final ServicoDao paramDAO ) {
		this.dao = paramDAO;
	}

	@Override
	public Servico findById( Long id ) {
		Servico ent = this.dao.findById( id );
		return ent;
	}

	@Override
	public List<Servico> findAll() {
		return this.dao.findAll();
	}

	@Override
	@Transactional
	public Servico save( Servico entity ) {
		return this.dao.save( entity );
	}

	@Override
	@Transactional
	public void delete( Servico entity ) {
		this.dao.delete( entity );

	}

	@Override
	public Servico persist( Servico entity ) {
		return this.dao.persist( entity );
	}

	@Override
	public Servico merge( Servico merge ) {
		return this.dao.merge( merge );
	}

	@Override
	public List<Servico> findAll( String nomeParam ) {
		return this.dao.findAllByProperty( "nome", nomeParam );
	}

}
