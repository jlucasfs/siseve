package br.com.mobiew.siseve.service;

import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.mobiew.siseve.dto.AtendimentoDTO;
import br.com.mobiew.siseve.dto.RelatorioDto;
import br.com.mobiew.siseve.model.entity.Atendimento;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.service.base.CRUDService;

public interface AtendimentoService extends CRUDService<Atendimento, Long> {

	List<AtendimentoDTO> findAllByPaciente( Long id );

	List<Atendimento> findAllByNomePaciente( String nome );

	List<Atendimento> findAllAtendimentosByPaciente( Long id );

	CartesianChartModel findAllAtendimentosSeisMeses();
	
	PieChartModel findAllAtendimentosPorServico();

	List<RelatorioDto> findAllRelatorio( Evento evento );
}
