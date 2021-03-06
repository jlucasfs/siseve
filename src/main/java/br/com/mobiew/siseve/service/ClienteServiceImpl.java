package br.com.mobiew.siseve.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.model.dao.ClienteDAO;
import br.com.mobiew.siseve.model.entity.Atendimento;
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
			Hibernate.initialize( result.getAtendimentos() );
			for ( Atendimento atend : result.getAtendimentos() ) {
				Hibernate.initialize( atend.getServico() );
			}
		} catch ( Exception e ) {
			LOG.error( "Erro ao obter cliente pelo id - " + (id==null? "" : id) + " - " + e.getMessage() );
		}
        return result;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> lista = this.clienteDAO.findAll();
        inicializarOrdenarListaClientes( lista );
        return lista;
    }

	private void inicializarOrdenarListaClientes( List<Cliente> lista ) {
		if ( lista != null ) {
	        for ( Cliente cliente: lista ) {
				Hibernate.initialize( cliente.getAtendimentos() );
				for ( Atendimento atend : cliente.getAtendimentos() ) {
					Hibernate.initialize( atend.getServico() );
				}
			}
        }
        Collections.sort( lista, new Comparator<Cliente>() {
			@Override
			public int compare( Cliente o1Param, Cliente o2Param ) {
				return o1Param.getNome().compareTo( o2Param.getNome() );
			}
		});
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
    public List<Cliente> findAll( String nome, String sexo, Integer idadeInicial, Integer idadeFinal ) {
    	List<Cliente> result = new ArrayList<>();
        List<Cliente> lista = this.clienteDAO.findAll( nome, sexo, idadeInicial, idadeFinal );
    	if ( CollectionUtils.isNotEmpty( lista ) ) {
    		for ( Cliente cliente: lista ) {
    			boolean adiciona = false;
    			if ( idadeInicial == null ) {
    				if ( idadeFinal == null ) {
    					adiciona = true;
    				} else {
    	    			adiciona = cliente.getIdade().compareTo( idadeFinal ) <= 0;
    	    		}
    	    	} else {
    	    		if ( idadeFinal == null ) {
    	    			adiciona = cliente.getIdade().compareTo( idadeInicial ) >= 0;
    	    		} else {
    	    			adiciona = cliente.getIdade().compareTo( idadeInicial ) >= 0 && cliente.getIdade().compareTo( idadeFinal ) <= 0;
    	    		}
    	    	}
    			if ( adiciona ) {
    				result.add( cliente );
    			}
			}
    	}
        inicializarOrdenarListaClientes( result );
        return result;
    }

    @Override
    public PacienteDTO findPacientes() {
        PacienteDTO result = new PacienteDTO();
        List<PacienteDTO> lista = this.clienteDAO.findAllPacientes();
        Long total = lista == null ? 0L : lista.size();
        result.setTotalPacientes( total );
        return result;
    }
    
    public List<PacienteDTO> findAllPacientes() {
    	List<PacienteDTO> result = this.clienteDAO.findAllPacientes();
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
