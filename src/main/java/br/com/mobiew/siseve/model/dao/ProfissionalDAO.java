package br.com.mobiew.siseve.model.dao;

import java.util.List;

import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.model.entity.Usuario;

public interface ProfissionalDAO extends GenericDAO<Profissional, Long> {

	List<Profissional> findAll( String nome, String cpf );

	Profissional findByUsuario( Usuario usuario );
}
