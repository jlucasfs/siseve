package br.com.mobiew.siseve.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import br.com.mobiew.siseve.exceptions.NegocioException;
import br.com.mobiew.siseve.exceptions.SistemaException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public final class Util {

    private static final String SEPARADOR_HORAS = ":";

    // private static final String SEPARADOR_PAGINA = "-";
    private static final String SEPARADOR_ANO_MES = "/";

    /**
     * Constroi a instancia de 'Util'.
     */
    private Util() {
        super();
    }

    /**
     * Transforma uma lista de objetos em um mapa, onde a chave � o resultado da fun��o toString().
     * 
     * @param colecao Lista de objetos
     * @return mapa com os objetos.
     */
    public static <T> Map<String, T> obterMapa( final Collection<T> colecao ) {
        final Map<String, T> mapa = new HashMap<String, T>();
        for ( T item: colecao ) {
            mapa.put( item.toString(), item );
        }
        return mapa;
    }

    /**
     * Transforma uma colecao de objetos em uma lista de SelectItem para exibicao na tela, tendo como texto a ser exibido o objeto transformado em string
     * (toString()).
     * 
     * @param colecao Lista de objetos
     * @return Lista de SelectItem
     */
    @SuppressWarnings( "rawtypes" )
    public static List<SelectItem> obterItens( final Collection colecao ) {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        for ( Object item: colecao ) {
            lista.add( new SelectItem( item.toString() ) );
        }
        return lista;
    }

    public static OutputStream createOutputStreamPDF( final String nomeArquivoParam ) throws IOException {
        HttpServletResponse response = getResponsePDF();
        response.setHeader( "Content-Disposition", "Attachment; Filename=" + nomeArquivoParam );
        return response.getOutputStream();
    }
    
    public static OutputStream createOutputStreamPDFPagina( final String nomeArquivo ) throws IOException {
        return getResponsePDFPagina( nomeArquivo ).getOutputStream();
    }

    public static HttpServletResponse getResponsePDFPagina( final String nomeArquivo ) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader( Constantes.TEXTO_CONTENT_DISPOSITION, "inline; filename=" + nomeArquivo + ".pdf" );
        response.setContentType( "application/pdf" );
        return response;
    }
    
    public static OutputStream createOutputStreamPDF( final String nomeArquivoParam, final int lengthParam ) throws IOException {
        HttpServletResponse response = getResponsePDF();
        response.setHeader( "Content-Disposition", "Attachment; Filename=" + nomeArquivoParam );
        response.setContentLength( lengthParam );
        return response.getOutputStream();
    }

    public static OutputStream createOutputStreamXls( final String nomeArquivoParam, final int lengthParam ) throws IOException {
    	HttpServletResponse response = getResponseXLS();
    	response.setHeader( "Content-Disposition", "Attachment; Filename=" + nomeArquivoParam );
    	response.setContentLength( lengthParam );
    	return response.getOutputStream();
    }

    public static HttpServletResponse getResponseXLS() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader( "Content-Type", "application/vnd.ms-excel" );
        return response;
    }

    public static HttpServletResponse getResponsePDF() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader( "Content-Type", "application/pdf" );
        return response;
    }

    /**
     * Guardar objeto em atributo de escopo de sessao.
     * 
     * @param atributo O nome do atributo.
     * @param obj O objeto a ser armazenado.
     */
    public static void guardarAtributoSessao( final String atributo, final Object obj ) {
        obterObjetoSessao().setAttribute( atributo, obj );
    }

    /**
     * Retorna o atributo de escopo de sessao.
     * 
     * @param atributo O nome do atributo.
     * @return O objeto
     */
    public static Object obterAtributoSessao( final String atributo ) {
    	Object result = null;
    	HttpSession session = obterObjetoSessao();
        if ( session != null ) {
        	result = session.getAttribute( atributo );
        }
        return result;
    }

    /**
     * Remove o atributo de escopo de sessao.
     * 
     * @param atributo O nome do atributo.
     */
    public static void removerAtributoSessao( final String atributo ) {
        obterObjetoSessao().removeAttribute( atributo );
    }

    /**
     * Obter objecto sessao do ServletActionContext (Struts Action).
     * 
     * @return Objeto de sessao.
     */
    public static HttpSession obterObjetoSessao() {
        HttpSession result = null;
        if ( FacesContext.getCurrentInstance() != null ) {
	        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        if ( request != null ) {
	            result = request.getSession();
	        }
        }
        return result;
    }

    /**
     * 
     * @return s
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static File createFile( final InputStream io, final String fileName ) throws IOException {
        File file = File.createTempFile( fileName, ".tmp" );
        file.deleteOnExit();
        FileOutputStream fos = new FileOutputStream( file );
        byte[] buf = new byte[1024];
        int read = 0;
        while ( ( read = io.read( buf ) ) > 0 ) {
            fos.write( buf, 0, read );
        }
        fos.close();
        return file;
    }

    /**
     * Método que retorna uma lista dos ultimos 12 - Mês/Ano
     * 
     * @return MM/yyyy String
     */
    public static SelectItem[] getUltimoMesAnoAtual() {
        SimpleDateFormat fmtComMask = new SimpleDateFormat( "MM/yyyy" );
        SimpleDateFormat fmtSemMask = new SimpleDateFormat( "yyyyMM" );
        Calendar calendar = new GregorianCalendar();
        SelectItem[] options = new SelectItem[12];
        for ( int i = 0; i < 12; i++ ) {
            if ( i != 0 ) {
                calendar.add( Calendar.MONTH, -1 );
            }
            options[i] = new SelectItem( fmtSemMask.format( calendar.getTime() ), fmtComMask.format( calendar.getTime() ) );
        }
        return options;
    }

    /**
     * Método que retira a formatação MM/yyyy
     * 
     * @return yyyyMM String
     */
    public static String comMascaraMesAno( String value ) {
        if ( value == null || value.equals( "" ) || StringUtils.length( value ) != 6 ) {
            return "";
        }
        String anomes = value;
        String mesano = anomes.substring( 4, 6 ) + SEPARADOR_ANO_MES + anomes.substring( 0, 4 );
        return mesano.toString();
    }

    /**
     * Método que retorna hora e minuto formatados
     * 
     * @param hora String
     * @param minuto String
     * @return hh:mm String
     */
    public static String getHrMinutoFmt( Integer hora, Integer minuto ) throws NegocioException {

        if ( "".equals( hora ) || String.valueOf( hora ).length() > 2 || hora > 24 ) {
            throw new NegocioException( "Preencha [Quantidade de horas cursadas] corretamente." );
        }
        if ( "".equals( minuto ) || String.valueOf( minuto ).length() > 2 || minuto > 59 ) {
            throw new NegocioException( "Preencha [Quantidade de horas cursadas] corretamente." );
        }
        if ( hora >= 24 && minuto >= 59 ) {
            throw new NegocioException( "Preencha [Quantidade de horas cursadas] corretamente." );
        }

        StringBuilder sb = new StringBuilder();
        sb.append( String.format( "%02d", hora ) );
        sb.append( String.format( "%02d", minuto ) );

        return sb.toString();
        // sb.append(SEPARADOR_HORAS);
    }

    /**
     * Método que retira a formatação MM/yyyy
     * 
     * @return yyyyMM String
     */
    public static String getHrMinutoMask( String value ) {
        StringBuilder sb = new StringBuilder();
        if ( !"".equals( value ) ) {
            sb.append( value.substring( 0, 2 ) );
            sb.append( SEPARADOR_HORAS );
            sb.append( value.substring( 2, 4 ) );
        }
        return sb.toString();
    }

    /**
     * Método que retira a formatação MM/yyyy
     * 
     * @return Hashtable<String,String> [hora,minuto]
     */
    public static Hashtable<String, Integer> getHoraMinuto( String pHoraMinuto ) {
        Hashtable<String, Integer> table = new Hashtable<String, Integer>();
        if ( pHoraMinuto != null && pHoraMinuto.length() == 4 ) {
            table.put( "hora", Integer.parseInt( pHoraMinuto.substring( 0, 2 ) ) );
            table.put( "minuto", Integer.parseInt( pHoraMinuto.substring( 2, 4 ) ) );
        }
        return table;
    }

    /**
     * Método estático que popula um compoente select/radio apartir de uma lista de um determinado objeto.
     * 
     * @param pMethod String
     * @param pListaTemp List<Object>
     * @throws SistemaException SistemaException
     * @return SelectItem[] SelectItem[]
     */
    @SuppressWarnings( "rawtypes" )
    public static SelectItem[] populaSelectItemsHeader( String pNameMethodID, String pNameMethodLabel, List pListaTemp ) throws SistemaException {
        SelectItem[] options = null;
        if ( pListaTemp != null && !pListaTemp.isEmpty() ) {
            try {
                String nomeMetodoID = "get" + pNameMethodID.substring( 0, 1 ).toUpperCase() + pNameMethodID.substring( 1 );
                String nomeMetodoLabel = "get" + pNameMethodLabel.substring( 0, 1 ).toUpperCase() + pNameMethodLabel.substring( 1 );
                if ( "".equals( nomeMetodoLabel ) || "".equals( nomeMetodoID ) ) {
                    throw new SistemaException( "Erro ao obter o nome do método." );
                }
                options = new SelectItem[pListaTemp.size() + 1];
                int i = 0;
                options[i] = new SelectItem( "", "Selecione..." );
                for ( Object objectTemp: pListaTemp ) {
                    if ( objectTemp.getClass().getMethod( nomeMetodoID ).invoke( objectTemp ) == null ) {
                        throw new SistemaException( "Erro ao obter o label do objeto! Favor o nome do atributo [" + nomeMetodoID + "]." );
                    }
                    if ( objectTemp.getClass().getMethod( nomeMetodoLabel ).invoke( objectTemp ) == null ) {
                        throw new SistemaException( "Erro ao obter o label do objeto! Favor o nome do atributo [" + nomeMetodoLabel + "]." );
                    }
                    options[++i] = new SelectItem( objectTemp.getClass().getMethod( nomeMetodoID ).invoke( objectTemp ), (String) objectTemp.getClass()
                            .getMethod( nomeMetodoLabel ).invoke( objectTemp ) );
                }

            } catch ( NoSuchMethodException nsme ) {
                throw new SistemaException( nsme.getMessage() );
            } catch ( InvocationTargetException ine ) {
                throw new SistemaException( ine.getMessage() );
            } catch ( IllegalAccessException ile ) {
                throw new SistemaException( ile.getMessage() );
            }

        }

        return options;
    }

    /**
     * Método estático que popula um compoente select/radio apartir de uma lista de um determinado objeto
     * 
     * @param pMethod String
     * @param pListaTemp List<Object>
     * @throws SistemaException SistemaException
     * @return SelectItem[] SelectItem[]
     */
    @SuppressWarnings( "rawtypes" )
    public static SelectItem[] populaSelectItems( String pNameMethodID, String pNameMethodLabel, List pListaTemp ) throws SistemaException {

        SelectItem[] options = null;

        if ( pListaTemp != null && !pListaTemp.isEmpty() ) {

            try {

                String nomeMetodoID = "get" + pNameMethodID.substring( 0, 1 ).toUpperCase() + pNameMethodID.substring( 1 );
                String nomeMetodoLabel = "get" + pNameMethodLabel.substring( 0, 1 ).toUpperCase() + pNameMethodLabel.substring( 1 );
                if ( "".equals( nomeMetodoLabel ) || "".equals( nomeMetodoID ) ) {
                    throw new SistemaException( "Erro ao obter o nome do método." );
                }
                options = new SelectItem[pListaTemp.size()];
                int i = 0;
                for ( Object objectTemp: pListaTemp ) {
                    if ( objectTemp.getClass().getMethod( nomeMetodoID ).invoke( objectTemp ) == null ) {
                        throw new SistemaException( "Erro ao obter o label do objeto! Favor o nome do atributo [" + nomeMetodoID + "]." );
                    }
                    if ( objectTemp.getClass().getMethod( nomeMetodoLabel ).invoke( objectTemp ) == null ) {
                        throw new SistemaException( "Erro ao obter o label do objeto! Favor o nome do atributo [" + nomeMetodoLabel + "]." );
                    }
                    options[i++] = new SelectItem( objectTemp.getClass().getMethod( nomeMetodoID ).invoke( objectTemp ), (String) objectTemp.getClass()
                            .getMethod( nomeMetodoLabel ).invoke( objectTemp ) );
                }

            } catch ( NoSuchMethodException nsme ) {
                throw new SistemaException( nsme.getMessage() );
            } catch ( InvocationTargetException ine ) {
                throw new SistemaException( ine.getMessage() );
            } catch ( IllegalAccessException ile ) {
                throw new SistemaException( ile.getMessage() );
            }

        }

        return options;
    }

    /**
     * Método estático que popula um compoente select/radio apartir de uma lista de um determinado objeto
     * 
     * @param pClass pEnum<Object>
     * @param pNameMethod String
     * @throws SistemaException SistemaException
     * @return SelectItem[] SelectItem[]
     */
    @SuppressWarnings( "rawtypes" )
    public static SelectItem[] populaSelectItems( String pNameMethod, Class pEnum ) throws SistemaException {

        SelectItem[] options = null;

        if ( pEnum != null ) {

            try {
                Object[] enumerations = pEnum.getEnumConstants();

                options = new SelectItem[enumerations.length];
                String nomeMetodo = "get" + pNameMethod.substring( 0, 1 ).toUpperCase() + pNameMethod.substring( 1 );

                for ( int i = 0; i < enumerations.length; i++ ) {
                    options[i] = new SelectItem( enumerations[i], (String) enumerations[i].getClass().getMethod( nomeMetodo ).invoke( enumerations[i] ) );
                }

            } catch ( NoSuchMethodException nsme ) {
                throw new SistemaException( nsme.getMessage() );
            } catch ( InvocationTargetException ine ) {
                throw new SistemaException( ine.getMessage() );
            } catch ( IllegalAccessException ile ) {
                throw new SistemaException( ile.getMessage() );
            }

        }

        return options;
    }

    /**
     * Método estático que popula um compoente select/radio apartir de uma lista de um determinado objeto
     * 
     * @param pClass pEnum<Object>
     * @param pNameMethod String
     * @throws SistemaException SistemaException
     * @return SelectItem[] SelectItem[]
     */
    @SuppressWarnings( "rawtypes" )
    public static SelectItem[] populaFilterHeader( String pNameMethod, Class pEnum ) throws SistemaException {

        SelectItem[] options = null;

        if ( pEnum != null ) {

            try {
                Object[] enumerations = pEnum.getEnumConstants();

                options = new SelectItem[enumerations.length + 1];
                String nomeMetodo = "get" + pNameMethod.substring( 0, 1 ).toUpperCase() + pNameMethod.substring( 1 );

                int j = 0;
                options[j] = new SelectItem( "", "Selecione..." );
                for ( int i = 0; i < enumerations.length; i++ ) {
                    options[++j] = new SelectItem( enumerations[i], (String) enumerations[i].getClass().getMethod( nomeMetodo ).invoke( enumerations[i] ) );
                }

            } catch ( NoSuchMethodException nsme ) {
                throw new SistemaException( nsme.getMessage() );
            } catch ( InvocationTargetException ine ) {
                throw new SistemaException( ine.getMessage() );
            } catch ( IllegalAccessException ile ) {
                throw new SistemaException( ile.getMessage() );
            }

        }

        return options;
    }

    public static void exibirMensagemErro( final String msg ) {
        FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, msg, null ) );
    }

    public static Double calcularValorTruncado( Double valorParam, int numeroCasasDecimals ) {
        BigDecimal valor = BigDecimal.valueOf( valorParam );
        valor = valor.setScale( numeroCasasDecimals, RoundingMode.CEILING );
        return valor.doubleValue();
    }

    public static String convertToNormalize( final String texto ) {
        if ( texto == null ) {
            return StringUtils.EMPTY;
        }
        return Normalizer.normalize( texto.trim(), Normalizer.Form.NFD ).replaceAll( "[^\\p{ASCII}]", "" );
    }
    
    public static String converterConteudoHtml( final String conteudoParam ) {
        String result = "";
        String[] results = conteudoParam.split( "" );
        for ( String car : results ) {
            Matcher matcher = Pattern.compile( "[\\wáàãâéêíóôõúüçÁÀÃÂÉÊÍÓÔÕÚÜÇ]", Pattern.CASE_INSENSITIVE ).matcher( car );
            if ( matcher.find() ) {
                car = StringEscapeUtils.escapeHtml( car );
            } else if ( !StringUtils.isAsciiPrintable( car ) ) {
                car = " ";
            }
            result += car;
        }
        return result;
    }
    
    public static void gerarRespostaXls( final HttpServletResponse response, final JasperPrint print, final String fileName ) throws Exception {
        JRXlsExporter exporter = new JRXlsExporter();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        exporter.setParameter( JRXlsExporterParameter.JASPER_PRINT, impressao );
//        exporter.setParameter( JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE );
//        exporter.setParameter( JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE );
//        exporter.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE );
//        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, xlsReport );
        exporter.setConfiguration( getReportConfigurationXls() );
        exporter.setExporterInput( new SimpleExporterInput( print ) );
		exporter.setExporterOutput( new SimpleOutputStreamExporterOutput( os ) );
        exporter.exportReport();
        
        response.setContentType( "application/vnd.ms-excel" );
        response.setHeader( "Content-disposition", "inline; filename=\"" + fileName + "\"" );

        final byte[] relatorio = os.toByteArray();
        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write( relatorio, 0, relatorio.length );
        ouputStream.flush();
        ouputStream.close();
    }

	private static SimpleXlsReportConfiguration getReportConfigurationXls() {
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet( false );
		configuration.setDetectCellType( false );
		configuration.setCollapseRowSpan( false );
		configuration.setRemoveEmptySpaceBetweenRows( true );
		configuration.setWhitePageBackground( false );
		return configuration;
	}
}
