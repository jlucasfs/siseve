package br.com.mobiew.siseve.service;

import java.util.List;

import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.model.entity.Usuario;
import br.com.mobiew.siseve.service.base.CRUDService;

public interface ProfissionalService extends CRUDService<Profissional, Long> {

	List<Profissional> findAll( String nome, String cpf );

	Profissional findByUsuario( Usuario usuario );
}
