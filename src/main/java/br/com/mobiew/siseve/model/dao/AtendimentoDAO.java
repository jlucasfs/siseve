package br.com.mobiew.siseve.model.dao;

import java.util.List;

import br.com.mobiew.siseve.dto.AtendimentoDTO;
import br.com.mobiew.siseve.dto.RelatorioDto;
import br.com.mobiew.siseve.model.entity.Atendimento;
import br.com.mobiew.siseve.model.entity.Evento;

public interface AtendimentoDAO extends GenericDAO<Atendimento, Long> {

	List<RelatorioDto> findAllRelatorio( Evento eventoParam );
	
	List<AtendimentoDTO> findAllAtendimentosSeisMeses();
}
