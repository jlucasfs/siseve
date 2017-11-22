package br.com.mobiew.siseve.model.dao;

import java.util.List;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente, Long> {

    List<Cliente> findAll( Cliente cliente );
    
    List<Cliente> findAll( String nome, String sexo, Integer idadeInicial, Integer idadeFinal );
    
    List<PacienteDTO> findAllPacientes() ;
    
    PacienteDTO findDadosGenericosById( Long idParam );
}
