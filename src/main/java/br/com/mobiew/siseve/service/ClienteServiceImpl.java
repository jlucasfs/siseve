package br.com.mobiew.siseve.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.dao.ClienteDAO;
import br.com.mobiew.siseve.model.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger LOG = Logger.getLogger( ClienteServiceImpl.class );

	private ClienteDAO clienteDAO;

    @Autowired
    public ClienteServiceImpl( final ClienteDAO paramDAO ) {
        this.clienteDAO = paramDAO;
    }

    @Override
    public Cliente findById( Long id ) {
    	Cliente result = null;
    	try {
        	result = this.clienteDAO.findById( id );
		} catch ( Exception e ) {
			LOG.error( "Erro ao obter cliente pelo id - " + (id==null? "" : id) + " - " + e.getMessage() );
		}
        return result;
    }

    @Override
    public List<Cliente> findAll() {
        return this.clienteDAO.findAll();
    }

    @Override
    @Transactional
    public Cliente save( Cliente entity ) {
        return this.clienteDAO.save( entity );
    }

    @Override
    @Transactional
    public void delete( Cliente entity ) {
        this.clienteDAO.delete( entity );
    }

    @Override
    public List<Cliente> findAll( Cliente cliente ) {
        return this.clienteDAO.findAll( cliente );
    }

    @Override
    public Cliente persist( Cliente entity ) {
        return this.clienteDAO.persist( entity );
    }

    @Override
    public Cliente merge( Cliente merge ) {
        return this.clienteDAO.merge( merge );
    }

    @Override
    public List<Cliente> findAll( String nomeParam, String cpfParam, String tipoPessoaParam ) {
        List<Cliente> lista = this.clienteDAO.findAll( nomeParam, cpfParam, tipoPessoaParam );
        return lista;
    }

    @Override
    public PacienteDTO findPacientes() {
        PacienteDTO result = new PacienteDTO();
        result.setTotalPrivado( 0L );
        result.setTotalConvenio( 0L );
        result.setTotalPacientes( result.getTotalConvenio() + result.getTotalPrivado() );
        return result;
    }
    
    public List<PacienteDTO> findAllPacientes( Long idParam, String cpfParam, String tipoPessoaParam, Long idConvenio, Date dataInicio, Date dataFim ) {
    	List<PacienteDTO> result = this.clienteDAO.findAllPacientes( idParam, cpfParam, tipoPessoaParam, idConvenio, dataInicio, dataFim );
    	Collections.sort( result, new Comparator<PacienteDTO>() {
    		@Override
    		public int compare( PacienteDTO o1Param, PacienteDTO o2Param ) {
    			return o1Param.getNome().compareTo( o2Param.getNome() );
    		}
    	});
    	return result;
    }

    @Override
    public PacienteDTO findDadosGenericosById( Long idParam ) {
        return this.clienteDAO.findDadosGenericosById( idParam );
    }
}
