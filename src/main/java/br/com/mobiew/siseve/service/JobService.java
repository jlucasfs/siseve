package br.com.mobiew.siseve.service;

import java.io.Serializable;

public interface JobService extends Serializable {
	
	@SuppressWarnings( "rawtypes" )
	void startJob( Class classe, String jobName, String jobServiceName, String crontab, Object service ); 
}
