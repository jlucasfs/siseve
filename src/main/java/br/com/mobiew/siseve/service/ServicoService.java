package br.com.mobiew.siseve.service;

import java.util.List;

import br.com.mobiew.siseve.model.entity.Servico;
import br.com.mobiew.siseve.service.base.CRUDService;

public interface ServicoService extends CRUDService<Servico, Long> {

	List<Servico> findAll( String nome );

}
