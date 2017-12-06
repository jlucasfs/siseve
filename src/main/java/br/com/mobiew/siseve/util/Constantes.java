package br.com.mobiew.siseve.util;

import java.text.Collator;
import java.util.Locale;

/**
 * Classe Constantes responsavel por manter as constantes utilizadas no sistema.
 */
public final class Constantes {

    /**
     * Constroi a instancia de 'Constantes'.
     */
    private Constantes() {
        super();
    }

    /**
     * Nome da variavel do ambiente do sistema.
     */
    //public static final String VARIAVEL_AMBIENTE_APP_SERVER = "user.name";
    public static final String VARIAVEL_AMBIENTE_APP_SERVER = "ENV";

    /** Nome do arquivo config.properties. */
    public static final String BUNDLE_CONFIG = "config";

    /** Nome do arquivo resources.properties. */
    public static final String RESOURCES_CONFIG = "resources";

    /** Nome do arquivo messages.properties. */
    public static final String BUNDLE_MESSAGES = "messages";

    /** Ambiente de desenvolvimento local. */
    public static final String APP_AMBIENTE_LOCAL = "local";

    /** Ambiente de desenvolvimento remoto. */
    public static final String APP_AMBIENTE_REMOTO = "remoto";

    /** Ambiente de desenvolvimento. */
    public static final String APP_AMBIENTE_DESENVOLVIMENTO = "desenvolvimento";

    /** Ambiente de homologacao. */
    public static final String APP_AMBIENTE_HOMOLOGACAO = "homologacao";

    /** Ambiente de producao. */
    public static final String APP_AMBIENTE_PRODUCAO = "producao";

    /** Titulo para inibir a verificacao de avisos (warnings). */
    public static final String UNCHECKED = "unchecked";

    /** Locale Ingles-Estados Unidos */
    public static final Locale LOCALE_EN_US = new Locale( "en", "US" );

    /** Locale Espanhol */
    public static final Locale LOCALE_ES = new Locale( "es" );

    /** Locale Portugues-Brasil */
    public static final Locale LOCALE_PT_BR = new Locale( "pt", "BR" );

    /** Titulo da aplicacao. */
    public static final String APP_TITULO = "CONSULT";

    /** */
    public static final String EXTENSAO_XML = ".xml";

    /** */
    public static final String EXTENSAO_EXCEL = ".xls";

    /** Mensagem de erro ao recuperar objetos. */
    public static final String MSG_ERRO_RECUPERAR_OBJETOS = "erro.recuperarObjetos";

    /** Mensagem de erro ao incluir dados no banco. */
    public static final String MSG_ERRO_AO_INCLUIR_DADOS = "erro.aoIncluirDados";

    /** Mensagem de erro ao atualizar dados no banco. */
    public static final String MSG_ERRO_AO_ATUALIZAR_DADOS = "erro.aoAtualizarDados";

    /** Mensagem de erro ao excluir dados no banco. */
    public static final String MSG_ERRO_AO_EXCLUIR_DADOS = "erro.aoExcluirDados";

    /** Mensagem de erro ao obter objeto sessao. */
    public static final String MSG_ERRO_OBTER_OBJ_SESSAO = "erro.obterObjSessao";

    /** Mensagem de erro ao obter objeto sessao para lista. */
    public static final String MSG_ERRO_OBTER_OBJ_SESSAO_LISTA = "erro.obterObjSessaoLista";

    /** Mensagem de erro ao obter objeto sessao. */
    public static final String MSG_ERRO_OBTER_LISTA = "erro.obterLista";

    /** Mensagem de erro ao obter objeto sessao. */
    public static final String MSG_ERRO_OBTER_LISTA_SESSAO = "erro.obterListaSessao";

    /** Mensagem de erro ao executar consulta. */
    public static final String MSG_ERRO_EXEC_CONSULTA = "erro.execConsulta";

    /** Mensagem de erro ao executar consulta em uma sessao. */
    public static final String MSG_ERRO_EXEC_CONSULTA_SESSAO = "erro.execConsultaSessaoNula";

    /** Mensagem de erro ao executar consulta c/ obj. sessao. */
    public static final String MSG_ERRO_EXEC_CONSULTA_OBJSESSAO = "erro.execConsultaObjSessao";

    /** Mensagem de erro de unique constraint. */
    public static final String MSG_ERRO_UNIQUE_CONSTRAINT = "erro.uniqueConstraint";

    /** Mensagem de erro do hibernate. */
    public static final String MSG_ERRO_HIBERNATE = "erro.hibernate";

    /** Mensagem de erro ao fechar a sess�o do hibernate. */
    public static final String MSG_ERRO_SESSAO_HIBERNATE = "erro.sessaoHibernate";

    /** Mensagem de erro nao esperado. */
    public static final String MSG_ERRO_NAO_ESPERADO = "erro.desconhecido";

    /** Mensagem de erro de viola��o de constraint. */
    public static final String MSG_ERRO_CONSTRAINT_VIOLATION = "erro.constraintViolation";

    /** Nome do atributo onde esta armazenado o usuario autenticado. */
    public static final String USUARIO_AUTENTICADO = "usuarioAutenticado";

    public static final String FILE_SEPARATOR = System.getProperty( "file.separator" );

    public static final String ARQUIVO_LAYOUT_RETORNO = "/retornoBanco.xml";

    public static final String ARQUIVO_LAYOUT_RETORNO_SANTANDER = "/retornoSantander.xml";
    
    public static final String ARQUIVO_LAYOUT_REMESSA = "/remessaBanco.xml";

    /** Nome do atributo onde esta armazenado o perfil do usuario autenticado. */
    public static final String PERFIL_USUARIO_AUTENTICADO = "perfilUsuarioAutenticado";

    /** Nome da variavel do JasperReport para definir o Locale - 'REPORT_LOCALE'. */
    public static final String PARAMETRO_REPORT_LOCALE = "REPORT_LOCALE";

    public static Collator COLLATOR = Collator.getInstance( Constantes.LOCALE_PT_BR );
    
    public static final String TEXTO_CONTENT_DISPOSITION = "Content-Disposition";

    public static final String EXTENSAO_XLS = "xls";
    
    static {
        Constantes.COLLATOR.setStrength( Collator.PRIMARY );
    }
}
