package br.com.mobiew.siseve.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.mobiew.siseve.util.ConstantesData;

public class PacienteDTO implements Serializable {

    private static final long serialVersionUID = -7253532759622254533L;

    private Long id;

    private Date dataInclusao;

    private String nome;

    private String numeroCarteira;

    private String cpf;

    private String cnpj;

    private Long totalPacientes;

    private String endereco;

    private String numero;

    private String complemento;

    public Long getId() {
        return this.id;
    }

    public void setId( Long idParam ) {
        this.id = idParam;
    }

    public Date getDataInclusao() {
        return this.dataInclusao;
    }

    public String getDataInclusaoFormatada() {
    	String result = null;
    	if ( this.dataInclusao != null ) {
    		result = ConstantesData.FMT_DATA.format( this.dataInclusao );
    	}
    	return result;
    }

    public void setDataInclusao( Date dataInclusaoParam ) {
        this.dataInclusao = dataInclusaoParam;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome( String nomeParam ) {
        this.nome = nomeParam;
    }

    public String getNumeroCarteira() {
        return this.numeroCarteira;
    }

    public void setNumeroCarteira( String numeroCarteiraParam ) {
        this.numeroCarteira = numeroCarteiraParam;
    }

    public Long getTotalPacientes() {
        return this.totalPacientes;
    }

    public void setTotalPacientes( Long totalPacientesParam ) {
        this.totalPacientes = totalPacientesParam;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf( String cpfParam ) {
        this.cpf = cpfParam;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj( String cnpjParam ) {
        this.cnpj = cnpjParam;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco( String enderecoParam ) {
        this.endereco = enderecoParam;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero( String numeroParam ) {
        this.numero = numeroParam;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento( String complementoParam ) {
        this.complemento = complementoParam;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.id == null ) ? 0 : this.id.hashCode() );
        result = prime * result + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        PacienteDTO other = (PacienteDTO) obj;
        if ( this.id == null ) {
            if ( other.id != null ) return false;
        } else if ( !this.id.equals( other.id ) ) return false;
        if ( this.nome == null ) {
            if ( other.nome != null ) return false;
        } else if ( !this.nome.equals( other.nome ) ) return false;
        return true;
    }
}
