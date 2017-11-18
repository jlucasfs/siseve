package br.com.mobiew.siseve.model.dao;

import java.util.List;

import br.com.mobiew.siseve.model.entity.Servico;

public interface ServicoDao extends GenericDAO<Servico, Long> {

	List<Servico> findAll( String nome );
}
