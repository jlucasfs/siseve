package br.com.mobiew.siseve.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.mobiew.siseve.service.JobService;
import br.com.mobiew.siseve.service.UsuarioService;
import br.com.mobiew.siseve.util.job.KeepAliveJob;

@Controller
@ApplicationScoped
public class JobController {

	@Autowired
	private JobService jobService;

	@Autowired
	private UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		// CRONTAB: SEGUNDO, MINUTO, HORA, DIA DO MES, MES, DIA DA SEMANA, ANO (OPCIONAL)
		// Iniciar job keep alive para manter a conexao com mysql viva a cada hora.
		this.jobService.startJob( KeepAliveJob.class, KeepAliveJob.JOB_NAME, KeepAliveJob.SERVICE_NAME, "0 0 */1 * * ?", this.usuarioService );
	}
}
