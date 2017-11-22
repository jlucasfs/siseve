package br.com.mobiew.siseve.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.dto.AtendimentoDTO;
import br.com.mobiew.siseve.model.dao.AtendimentoDAO;
import br.com.mobiew.siseve.model.entity.Atendimento;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private AtendimentoDAO dao;

    @Autowired
    public AtendimentoServiceImpl( final AtendimentoDAO paramDAO ) {
        this.dao = paramDAO;
    }

    @Override
    public Atendimento findById( Long id ) {
        Atendimento atd = this.dao.findById( id );
        Hibernate.initialize( atd.getCliente() );
        if ( atd.getProfissional() != null ) {
            Hibernate.initialize( atd.getProfissional() );
        }
        return atd;
    }

    @Override
    public List<Atendimento> findAll() {
        return this.dao.findAll();
    }

    @Override
    @Transactional
    public Atendimento save( Atendimento entity ) {
        return this.dao.save( entity );
    }
    
    @Override
    @Transactional
    public void delete( Atendimento entity ) {
        this.dao.delete( entity );
    }

    @Override
    public Atendimento persist( Atendimento entity ) {
        return this.dao.persist( entity );
    }

    @Override
    public Atendimento merge( Atendimento merge ) {
        return this.dao.merge( merge );
    }

    @Override
    public List<AtendimentoDTO> findAllByPaciente( Long idParam ) {
        return toAtendimentoDTO( findAllAtendimentosByPaciente( idParam ) );
    }
    
    @Override
    public List<Atendimento> findAllAtendimentosByPaciente( Long idParam ) {
        List<Atendimento> atendimentos  = this.dao.findAllByProperty( "cliente.id", idParam );
        for ( Atendimento atendimento: atendimentos ) {
            Hibernate.initialize( atendimento.getProfissional() );
        }
        Collections.sort( atendimentos, new Comparator<Atendimento>() {
            public int compare( Atendimento o1, Atendimento o2 ) {
                return o2.getDataAtendimento().compareTo( o1.getDataAtendimento() );
            }
        } );
        return atendimentos;
    }
    
    private List<AtendimentoDTO> toAtendimentoDTO( List<Atendimento> atendimentosParam ) {
        List<AtendimentoDTO> result = new ArrayList<AtendimentoDTO>();
        if ( CollectionUtils.isNotEmpty( atendimentosParam ) ) {
            for ( Atendimento atd: atendimentosParam ) {
                AtendimentoDTO dto = new AtendimentoDTO();
                dto.setDataAtendimento( atd.getDataAtendimento() );
                dto.setIdAtendimento( atd.getId() );
                dto.setIdPaciente( atd.getCliente().getId() );
                dto.setNomeProfissional( atd.getProfissional() == null ? StringUtils.EMPTY : atd.getProfissional().getNome() );
                dto.setNomePaciente( atd.getCliente().getNome() );
                result.add( dto );
            }
        }
        return result;
    }

    public Atendimento findUltimoAtendimento( Long idPacienteParam ) {
        Atendimento result = null;
        List<Atendimento> lista = findAllAtendimentosByPaciente( idPacienteParam );
        if ( lista != null && !lista.isEmpty() ) {
            result = lista.get( 0 );
        }
        return result;
    }

	@Override
	public List<Atendimento> findAllByNomePaciente( String nomeParam ) {
		List<Atendimento> atendimentos  = this.dao.findAllByProperty( "cliente.nome", nomeParam );
		for ( Atendimento atendimento: atendimentos ) {
			Hibernate.initialize( atendimento.getProfissional() );
		}
		Collections.sort( atendimentos, new Comparator<Atendimento>() {
			public int compare( Atendimento o1, Atendimento o2 ) {
				return o2.getDataAtendimento().compareTo( o1.getDataAtendimento() );
			}
		} );
		return atendimentos;
	}

	@Override
	public CartesianChartModel findAllAtendimentosSeisMeses() {
		CartesianChartModel lista = new CartesianChartModel();
		return lista;
	}

	@Override
	public PieChartModel findAllAtendimentosPorServico() {
		PieChartModel pie = new PieChartModel();
		return pie;
	}
}
