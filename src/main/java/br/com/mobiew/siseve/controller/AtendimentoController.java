package br.com.mobiew.siseve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.dto.AtendimentoDTO;
import br.com.mobiew.siseve.model.entity.Cliente;
import br.com.mobiew.siseve.service.AtendimentoService;
import br.com.mobiew.siseve.service.ClienteService;
import br.com.mobiew.siseve.util.scopes.Scopes;

@Controller
@Scope( Scopes.SESSION )
public class AtendimentoController {

    private static final Logger LOG = Logger.getLogger( AtendimentoController.class );

    private String tituloTela = "Atendimentos";

    @Autowired
    private AtendimentoService atendimentoService;
    
    @Autowired
    private ClienteService clienteService;

    private List<Cliente> clientes;

    private List<Cliente> listaCliente;

    private Cliente cliente = new Cliente();

    private String nomePaciente;

    private Date dataAtual = new Date();

    private List<AtendimentoDTO> atendimentos;

    @PostConstruct
    public void inicializar() {
        this.cliente = new Cliente();
        this.nomePaciente = null;
        this.listaCliente = this.clienteService.findAll();
        consultar();
    }

    public String entrar() {
        inicializar();
        return "/pages/atendimento/atendimento-index?faces-redirect=true";
    }

    public void limparEdit() {
        inicializar();
    }

    public void consultar() {
        try {
            this.atendimentoService.findAllByNomePaciente( this.nomePaciente );
        } catch ( Exception e ) {
            LOG.error( e.getMessage() );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro ao consultar os pacientes. ", null ) );
        }
    }

    public List<String> completePaciente( String query ) {
        List<String> results = new ArrayList<String>();
        if ( query != null && this.listaCliente != null && !this.listaCliente.isEmpty() ) {
            for ( Cliente a: this.listaCliente ) {
                if ( a.getNome().toUpperCase().indexOf( query.toUpperCase() ) >= 0 ) {
                    results.add( a.getNome() );
                }
            }
        }
        return results;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes( List<Cliente> clientesParam ) {
        clientes = clientesParam;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente( Cliente clienteParam ) {
        cliente = clienteParam;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual( Date dataAtualParam ) {
        dataAtual = dataAtualParam;
    }

    public List<Cliente> getListaCliente() {
        return this.listaCliente;
    }

    public void setListaCliente( List<Cliente> listaClienteParam ) {
        this.listaCliente = listaClienteParam;
    }

    public String getNomePaciente() {
        return this.nomePaciente;
    }

    public void setNomePaciente( String nomePacienteParam ) {
        this.nomePaciente = nomePacienteParam;
    }

    public List<AtendimentoDTO> getAtendimentos() {
        return this.atendimentos;
    }

    public void setAtendimentos( List<AtendimentoDTO> atendimentosParam ) {
        this.atendimentos = atendimentosParam;
    }

    public String getTituloTela() {
        return this.tituloTela;
    }

    public void setTituloTela( String tituloTelaParam ) {
        this.tituloTela = tituloTelaParam;
    }
}
