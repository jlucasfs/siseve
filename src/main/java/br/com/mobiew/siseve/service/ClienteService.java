package br.com.mobiew.siseve.service;

import java.util.List;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.entity.Cliente;
import br.com.mobiew.siseve.service.base.CRUDService;

public interface ClienteService extends CRUDService<Cliente, Long> {

    List<Cliente> findAll( Cliente cliente );

    List<Cliente> findAll( String nome, String sexo, Integer idadeInicial, Integer idadeFinal );

    PacienteDTO findPacientes();

    List<PacienteDTO> findAllPacientes();

    PacienteDTO findDadosGenericosById( Long idParam );
}
