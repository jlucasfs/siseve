package br.com.mobiew.siseve.service;

import java.util.List;

import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.service.base.CRUDService;

public interface EventoService extends CRUDService<Evento, Long> {

	List<Evento> findAll( String nome );

	Evento findEventoAtual();
	
	Evento copiarServicos( Evento evento );
}
