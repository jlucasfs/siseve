package br.com.mobiew.siseve.model.dao;

import java.util.Date;
import java.util.List;

import br.com.mobiew.siseve.model.entity.Evento;

public interface EventoDAO extends GenericDAO<Evento, Long> {

	List<Evento> find( String descricao, Date dtEvento );
}
