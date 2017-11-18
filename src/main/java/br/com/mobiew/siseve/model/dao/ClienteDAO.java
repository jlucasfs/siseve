package br.com.mobiew.siseve.model.dao;

import java.util.Date;
import java.util.List;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente, Long> {

    List<Cliente> findAll( Cliente cliente );
    
    List<Cliente> findAll( String nome, String cpf, String tipo );
    
    List<PacienteDTO> findAllPacientes( Long idParam, String cpfParam, String tipoPessoaParam, Long idConvenio, Date dataInicio, Date dataFim ) ;
    
    PacienteDTO findDadosGenericosById( Long idParam );
}
