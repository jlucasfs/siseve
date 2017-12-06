package br.com.mobiew.siseve.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.dto.PacienteDTO;
import br.com.mobiew.siseve.dto.RelatorioDto;
import br.com.mobiew.siseve.model.entity.Atendimento;
import br.com.mobiew.siseve.model.entity.Cliente;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.model.entity.Servico;
import br.com.mobiew.siseve.model.enuns.UFEnum;
import br.com.mobiew.siseve.service.ClienteService;
import br.com.mobiew.siseve.service.EventoService;
import br.com.mobiew.siseve.service.ServicoService;
import br.com.mobiew.siseve.util.Constantes;
import br.com.mobiew.siseve.util.MessagesHelper;
import br.com.mobiew.siseve.util.Util;
import br.com.mobiew.siseve.util.scopes.Scopes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Controller
@Scope( Scopes.SESSION )
public class ClienteController {

	private static final Logger LOG = Logger.getLogger( ClienteController.class );

	private String entidade = "Paciente";

	@Autowired
	private ClienteService clienteService;

	private List<Cliente> clientes;

	private List<PacienteDTO> listaCliente;

	private List<PacienteDTO> listaPacienteDto;

	private Cliente cliente = new Cliente();

	private Cliente clienteAtender;

	private String nome;

	private String cpf;

	private String cnpj;

	private Date dataAtual = new Date();

	private String tipoPessoa = "T";

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private ServicoService servicoService;

	private List<Evento> eventosSelecionados;

	private List<Evento> eventos;

	private Long idCliente;

	private Long idServico;

	@Autowired
	private MessagesHelper messages;

	private List<Cliente> pacientes;

	private PacienteDTO pacienteSelecionado;

	private int paginaAtual;

	private Date dataMinima;

	private String sexo;

	private Integer idadeInicial;

	private Integer idadeFinal;

	private Atendimento atendimento;

	private Atendimento atendimentoSelecionado;

	private Date dataAtendimento;
	
	private List<Servico> servicos;

	@PostConstruct
	public void inicializar() {
		this.cliente = new Cliente();
		this.cliente.setUf( "RJ" );
		this.nome = null;
		this.cpf = null;
		this.cnpj = null;
		this.tipoPessoa = "T";
		this.pacienteSelecionado = null;
		consultar();
		this.eventos = eventoService.findAll();
		this.eventosSelecionados = new ArrayList<Evento>();
		this.dataMinima = new DateTime().withDate( 1900, 1, 1 ).toDate();
		this.servicos = servicoService.findAllEventoAtual();
		Collections.sort( servicos, new Comparator<Servico>() {
			@Override
			public int compare( Servico o1Param, Servico o2Param ) {
				return o1Param.getNome().compareTo( o2Param.getNome() );
			}
		});
		this.atendimento = new Atendimento( this.cliente, new Servico(), null );
		this.idadeInicial = null;
		this.idadeFinal = null;
	}

	public String entrar() {
		inicializar();
		return "/pages/paciente-index?faces-redirect=true";
	}

	public String voltar() {
		return "/pages/paciente-index?faces-redirect=true";
	}

	public void limparEdit() {
		inicializar();
	}

	public void consultar() {
		try {
			this.pacientes = this.clienteService.findAll( nome, sexo, idadeInicial, idadeFinal );
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao consultar os pacientes. ", null ) );
		}
	}

	public List<PacienteDTO> completePaciente( String query ) {
		List<PacienteDTO> results = new ArrayList<PacienteDTO>();
		this.listaCliente = this.clienteService.findAllPacientes();
		if ( query != null && this.listaCliente != null && !this.listaCliente.isEmpty() ) {
			for ( PacienteDTO item: this.listaCliente ) {
				String nome1 = Util.convertToNormalize( item.getNome().toUpperCase( Constantes.LOCALE_PT_BR ) );
				String nome2 = Util.convertToNormalize( query.toUpperCase( Constantes.LOCALE_PT_BR ) );
				if ( nome1.indexOf( nome2 ) >= 0 ) {
					results.add( item );
				}
			}
		}
		return results;
	}

	public List<String> completeNomePaciente( String query ) {
		List<String> results = new ArrayList<String>();
		this.listaCliente = this.clienteService.findAllPacientes();
		if ( query != null && this.listaCliente != null && !this.listaCliente.isEmpty() ) {
			for ( PacienteDTO a: this.listaCliente ) {
				String nome1 = Util.convertToNormalize( a.getNome().toUpperCase( Constantes.LOCALE_PT_BR ) );
				String nome2 = Util.convertToNormalize( query.toUpperCase( Constantes.LOCALE_PT_BR ) );
				if ( nome1.indexOf( nome2 ) >= 0 ) {
					results.add( a.getNome() );
				}
			}
		}
		return results;
	}

	public String incluir() {
		this.cliente = new Cliente();
		this.cliente.setUf( "RJ" );
		this.tipoPessoa = "F";
		this.idCliente = null;
		this.eventosSelecionados = new ArrayList<Evento>();
		this.atendimento = new Atendimento( this.cliente, new Servico(), null );
		this.idServico = null;
		return editar();
	}

	public String editar() {
		if ( this.idCliente != null ) {
			this.cliente = this.clienteService.findById( this.idCliente );
			this.eventosSelecionados = new ArrayList<Evento>();
		}
		return "paciente-edit?faces-redirect=true";
	}

	public String salvar() {
		String result = salvarAtendimento();
		inicializar();
		return result;
	}

	public void salvarImprimirProntuario() {
		String salvar = salvarAtendimento();
		if ( salvar != null && salvar.indexOf( "paciente-index" ) >= 0 ) {
			imprimirProntuario();
		}
	}
	
	private String salvarAtendimento() {
		try {
			if ( StringUtils.isBlank( this.cliente.getNome() ) ) {
				ControllerUtil.addErrorMessage( null, "Campo Nome é obrigatório." );
				return null;
			}
			boolean inclusao = this.cliente.getId() == null;
			if ( StringUtils.isBlank( this.cliente.getSexo() ) ) {
				this.cliente.setSexo( null );
			}
			this.clienteService.save( this.cliente );
			ControllerUtil.addInfoMessage( null, "Paciente " + ( inclusao ? "incluído" : "alterado" ) + " com sucesso." );
			return "paciente-index?faces-redirect=true";
		} catch ( Exception e ) {
			ControllerUtil.addErrorMessage( null, "Erro ao salvar " + this.entidade + "." );
			e.getMessage();
			return null;
		}
	}

	public void exclusao() {
		this.cliente = this.clienteService.findById( this.idCliente );
	}

	public void excluir() {
		try {
			this.clienteService.delete( this.cliente );
			inicializar();
			ControllerUtil.addInfoMessage( null, "Paciente excluído com sucesso." );
		} catch ( Exception e ) {
			e.getMessage();
			ControllerUtil.addErrorMessage( null, "Erro ao excluir " + this.entidade + "." );
		}
	}

	public void incluirAtendimento() {
		if ( this.idServico == null || Long.valueOf( 0L ).equals( this.idServico ) ) {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "O campo serviço deve ser selecionado", null ) );
			return;
		}
		this.atendimento.setServico( this.servicoService.findById( this.idServico ) );
		this.atendimento.setCliente( this.cliente );
		if ( cliente.getAtendimentos().contains( atendimento ) ) {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR,
					"O atendimento do serviço " + atendimento.getServico().getNome() + " já se encontra na lista.", atendimento.getServico().getNome() ) );
			return;
		}
		this.cliente.getAtendimentos().add( atendimento );
		this.atendimento = new Atendimento( this.cliente, new Servico(), null );
		this.idServico = null;
	}

	public void removerAtendimento() {
		this.cliente.getAtendimentos().remove( this.atendimentoSelecionado );
	}

	public static List<UFEnum> getAllUFs() {
		return Arrays.asList( UFEnum.values() );
	}

    public void imprimirProntuario() {
    	if ( this.idCliente != null ) {
    		// neste caso, esta vindo do botao imprimir prontuario da tela de consulta
    		this.cliente = this.clienteService.findById( this.idCliente );
    	}
    	if ( this.cliente == null ) {
    		ControllerUtil.addErrorMessage( null, "Cliente não selecionado." );
    		return;
    	}
        if ( this.cliente.getAtendimentos() == null || this.cliente.getAtendimentos().isEmpty() ) {
            ControllerUtil.addErrorMessage( null, "Não há dados para gerar prontuário do paciente." );
            return;
        }
        String nomeRelatorio = "prontuario.jasper";
        InputStream arquivoJasperIS = getClass().getClassLoader().getResourceAsStream( "report/" + nomeRelatorio );
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put( Constantes.PARAMETRO_REPORT_LOCALE, Constantes.LOCALE_PT_BR );
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource( this.cliente.getAtendimentos() );
            JasperPrint print = JasperFillManager.fillReport( arquivoJasperIS, parameters, dataSource );
            OutputStream ous = Util.createOutputStreamPDFPagina( "prontuario.pdf" );
            JasperExportManager.exportReportToPdfStream( print, ous );
            ous.flush();
            ous.close();
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
            
            
//            JRPdfExporter exporter = new JRPdfExporter();
//			exporter.setExporterInput( new SimpleExporterInput( print ) );
//			exporter.setExporterOutput( new SimpleOutputStreamExporterOutput( "prontuario.pdf" ) );
//
//			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//			reportConfig.setSizePageToContent( true );
//			reportConfig.setForceLineBreakPolicy( false );
//
//			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
//			exportConfig.setMetadataAuthor( "" );
//			exportConfig.setEncrypted( true );
//			exportConfig.setAllowedPermissionsHint( "PRINTING" );
//
//			exporter.setConfiguration( reportConfig );
//			exporter.setConfiguration( exportConfig );
//			exporter.exportReport();
        } catch (JRException ex) {
        	LOG.error( "Erro ao gerar arquivo com o prontuario" );
        	FacesContext.getCurrentInstance().addMessage( FacesMessage.FACES_MESSAGES,
        			new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao gerar arquivo com o prontuário do paciente", ex.getMessage() ) );
        } catch ( Exception e ) {
            LOG.error( e.getMessage() );
            FacesContext.getCurrentInstance().addMessage( FacesMessage.FACES_MESSAGES,
                    new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao gerar prontuário do paciente", e.getMessage() ) );
        }
    }

    public void imprimirRelatorioGeral() {
        String nomeRelatorio = "relatorioGeral.jasper";
        InputStream arquivoJasperIS = getClass().getClassLoader().getResourceAsStream( "report/" + nomeRelatorio );
        try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put( Constantes.PARAMETRO_REPORT_LOCALE, Constantes.LOCALE_PT_BR );
			parameters.put( JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE );
			List<RelatorioDto> lista = new ArrayList<>();
			lista.add( new RelatorioDto( 1L, "JOAO", 30L ) );
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource( lista );
			JasperPrint print = JasperFillManager.fillReport( arquivoJasperIS, parameters, dataSource );
			//ByteArrayOutputStream ous = new ByteArrayOutputStream();
			OutputStream ous = Util.createOutputStreamXls( "relatoriogeral.xls", 1024 );
			
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setExporterInput( new SimpleExporterInput( print ) );
			exporter.setExporterOutput( new SimpleOutputStreamExporterOutput( ous ) );
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet( false );
			configuration.setDetectCellType( false );
			configuration.setCollapseRowSpan( false );
			configuration.setRemoveEmptySpaceBetweenRows( true );
			configuration.setWhitePageBackground( false );
			exporter.setConfiguration( configuration );
			exporter.exportReport();
			
//			result = new DefaultStreamedContent( new ByteArrayInputStream( ous.toByteArray() ), 
//					"application/" + Constantes.EXTENSAO_XLS,
//					"relatorioGeral." + Constantes.EXTENSAO_XLS );
			
			ous.flush();
            ous.close();
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
            
		} catch ( Exception e ) {
			LOG.error( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage( FacesMessage.FACES_MESSAGES,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao gerar relatorio geral", e.getMessage() ) );
		}
//        return result;
	}
    
	public String getEntidade() {
		return entidade;
	}

	public void setEntidade( String entidadeParam ) {
		entidade = entidadeParam;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes( List<Cliente> clientesParam ) {
		clientes = clientesParam;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nomeParam ) {
		nome = nomeParam;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpfParam ) {
		cpf = cpfParam;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente( Cliente clienteParam ) {
		cliente = clienteParam;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj( String cnpjParam ) {
		cnpj = cnpjParam;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual( Date dataAtualParam ) {
		dataAtual = dataAtualParam;
	}

	public String getTipoPessoa() {
		return this.tipoPessoa;
	}

	public void setTipoPessoa( String tipoPessoaParam ) {
		this.tipoPessoa = tipoPessoaParam;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos( List<Evento> eventosParam ) {
		this.eventos = eventosParam;
	}

	public List<Evento> getEventosSelecionados() {
		return this.eventosSelecionados;
	}

	public void setEventosSelecionados( List<Evento> eventosSelecionadosParam ) {
		this.eventosSelecionados = eventosSelecionadosParam;
	}

	public Cliente getClienteAtender() {
		return this.clienteAtender;
	}

	public void setClienteAtender( Cliente clienteAtenderParam ) {
		this.clienteAtender = clienteAtenderParam;
	}

	public List<PacienteDTO> getListaPacienteDto() {
		return this.listaPacienteDto;
	}

	public void setListaPacienteDto( List<PacienteDTO> listaPacienteDtoParam ) {
		this.listaPacienteDto = listaPacienteDtoParam;
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente( Long idClienteParam ) {
		this.idCliente = idClienteParam;
	}

	public List<PacienteDTO> getListaCliente() {
		return this.listaCliente;
	}

	public void setListaCliente( List<PacienteDTO> listaClienteParam ) {
		this.listaCliente = listaClienteParam;
	}

	public PacienteDTO getPacienteSelecionado() {
		return this.pacienteSelecionado;
	}

	public void setPacienteSelecionado( PacienteDTO pacienteSelecionadoParam ) {
		this.pacienteSelecionado = pacienteSelecionadoParam;
	}

	public MessagesHelper getMessages() {
		return this.messages;
	}

	public void setMessages( MessagesHelper messagesParam ) {
		this.messages = messagesParam;
	}

	public int getPaginaAtual() {
		return this.paginaAtual;
	}

	public void setPaginaAtual( int paginaAtualParam ) {
		this.paginaAtual = paginaAtualParam;
	}

	public Date getDataMinima() {
		return this.dataMinima;
	}

	public void setDataMinima( Date dataMinimaParam ) {
		this.dataMinima = dataMinimaParam;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo( String sexoParam ) {
		this.sexo = sexoParam;
	}

	public Integer getIdadeInicial() {
		return this.idadeInicial;
	}

	public void setIdadeInicial( Integer idadeInicialParam ) {
		this.idadeInicial = idadeInicialParam;
	}

	public Integer getIdadeFinal() {
		return this.idadeFinal;
	}

	public void setIdadeFinal( Integer idadeFinalParam ) {
		this.idadeFinal = idadeFinalParam;
	}

	public List<Cliente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes( List<Cliente> pacientesParam ) {
		this.pacientes = pacientesParam;
	}

	public Atendimento getAtendimentoSelecionado() {
		return this.atendimentoSelecionado;
	}

	public void setAtendimentoSelecionado( Atendimento atendimentoSelecionadoParam ) {
		this.atendimentoSelecionado = atendimentoSelecionadoParam;
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento( Atendimento atendimentoParam ) {
		this.atendimento = atendimentoParam;
	}

	public Date getDataAtendimento() {
		return this.dataAtendimento;
	}

	public void setDataAtendimento( Date dataAtendimentoParam ) {
		this.dataAtendimento = dataAtendimentoParam;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos( List<Servico> servicosParam ) {
		this.servicos = servicosParam;
	}

	public Long getIdServico() {
		return this.idServico;
	}

	public void setIdServico( Long idServicoParam ) {
		this.idServico = idServicoParam;
	}
}
