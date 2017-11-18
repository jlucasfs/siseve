package br.com.mobiew.siseve.util.job;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import br.com.mobiew.siseve.service.UsuarioService;

/**
 * KeepAliveJob runs the usuario service to keep alive the application connecting to mysql database.
 * Without this, the connection is lost after a few hours.
 */
@Component
public class KeepAliveJob implements Job, Serializable {

    private static final long serialVersionUID = -2933597695127012773L;

    private static final Logger LOG = Logger.getLogger( KeepAliveJob.class );
    
	public static final Integer INTERVAL = 30;
	
	public static final String JOB_NAME = "keepAliveJob";

	public static final String SERVICE_NAME = "keepAliveService";

    public void execute( JobExecutionContext context ) throws JobExecutionException {
        try {
        	LOG.warn( "KeepAlive inicio." );
            UsuarioService usuarioService = (UsuarioService) context.getScheduler().getContext().get( SERVICE_NAME );
            usuarioService.findByLoginSenha( "", "" );
            LOG.warn( "KeepAlive fim." );
        } catch ( Exception e ) {
        	e.printStackTrace();
        	LOG.error( "erro - " + e.getMessage() );
        }
    }
}
