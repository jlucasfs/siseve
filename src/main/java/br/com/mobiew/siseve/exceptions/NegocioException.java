package br.com.mobiew.siseve.exceptions;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Classe SistemaException responsavel por tratar excecoes do sistema.
 */
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private List<String> messages = Lists.newArrayList();

    /**
     * Metodo Construtor que exibe mensagens de erro.
     * 
     * @param mensagem Recebe como par�metro a mensagem.
     */
    public NegocioException( final String mensagem ) {
        super( mensagem );
    }
    
    public NegocioException( List mensagens ) {
        this.messages = mensagens;
    }
    
    /**
     * @param throwableParam throwableParam.
     */
    public NegocioException( final Throwable throwableParam ) {
        super( throwableParam );
    }

    /**
     * Metodo Construtor que exibe mensagens de erro e imprime o erro propagado.
     * 
     * @param mensagem Recebe como par�metro a mensagem.
     * @param objetoExcecao Recebe como par�metro o objeto propagado.
     */
    public NegocioException( final String mensagem, final Throwable objetoExcecao ) {
        super( mensagem, objetoExcecao );
        objetoExcecao.printStackTrace();
    }

    
    public List<String> getMessages() {
        return messages;
    }

    
    public void setMessages( List<String> messages ) {
        this.messages = messages;
    }
}
