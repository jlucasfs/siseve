package br.com.mobiew.siseve.service;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOG = Logger.getLogger( JobServiceImpl.class );

	@SuppressWarnings( {
			"unchecked", "rawtypes"
	} )
	public void startJob( Class classe, String jobName, String jobServiceName, String crontab, Object service ) {
		try {
			LOG.warn( jobName + " Started." );
			JobDetail job = JobBuilder.newJob( classe ).withIdentity( jobName ).build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule( CronScheduleBuilder.cronSchedule( crontab ) )
					.build();
			Scheduler sch = new StdSchedulerFactory().getScheduler();
			sch.getContext().put( jobServiceName, service );
			sch.start();
			sch.scheduleJob( job, trigger );
			LOG.warn( jobName + " completed." );
		} catch ( SchedulerException e ) {
			e.printStackTrace();
			LOG.error( "Erro ao iniciar agendamento do job " + jobName + " - " + e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			LOG.error( "Erro nao esperado ao tentar realizar o agendamento do job " + jobName + " - " + e.getMessage() );
		}
		
	}

}
