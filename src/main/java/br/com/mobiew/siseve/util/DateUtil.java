package br.com.mobiew.siseve.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

public final class DateUtil {

    public static final int TYPE_DIFF_DAYS = 1;

    public static final int TYPE_DIFF_MONTHS = 2;

    public static final int TYPE_DIFF_YEARS = 3;

    public static final String TXT_DATA_INICIO = "dataInicio";

    public static final String TXT_DATA_FIM = "dataFim";

    public static final String TXT_MES_ANT = "mesant";

    public static final String TXT_MES_ATUAL = "mesref";

    public static final String TXT_MES_POS = "mespos";
    
    public static final String TXT_ONTEM = "ontem";

    public static final String TXT_HOJE = "hoje";

    public static final String TXT_AMANHA = "amanha";

    /**
     * Constroi a instancia de 'DateUtil'.
     */
    private DateUtil() {
        super();
    }

    public static Calendar convertDateToCalendar( final Date date ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        return calendar;
    }

    public static Date addMonths( final Date dateParam, final int months ) {
        if ( dateParam == null ) {
            return new Date();
        }
        Date result = dateParam;
        DateTime dt = new DateTime( result );
        dt = dt.plusMonths( months );
        result = dt.toDate();
        return result;
    }

    /**
     * Retorna o numero de dias, meses ou anos entre uma data e outra.
     * 
     * @param dateStartParam A data inicial.
     * @param dateEndParam A data final.
     * @param typeDiffParam O tipo de diferenca de datas (1=dias; 2=meses; 3=anos).
     * @return O numero de dias, meses ou anos.
     */
    public static int diffDate( final Date dateStartParam, final Date dateEndParam, final int typeDiffParam ) {
        if ( typeDiffParam < 1 || typeDiffParam > 3 || dateStartParam == null || dateEndParam == null || dateStartParam.compareTo( dateEndParam ) >= 0 ) {
            return 0;
        }
        int result = 0;
        DateTime dt1 = new DateTime( dateStartParam );
        DateTime dt2 = new DateTime( dateEndParam );
        if ( typeDiffParam == DateUtil.TYPE_DIFF_DAYS ) {
            Days days = Days.daysBetween( dt1, dt2 );
            result = days.getDays();
        } else if ( typeDiffParam == DateUtil.TYPE_DIFF_MONTHS ) {
            Months months = Months.monthsBetween( dt1, dt2 );
            result = months.getMonths();
        } else {
            Years years = Years.yearsBetween( dt1, dt2 );
            result = years.getYears();
        }
        return result;
    }

    public static Date criarHora( int hora, int minuto ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date( System.currentTimeMillis() ) );

        calendar.set( Calendar.HOUR_OF_DAY, hora );
        calendar.set( Calendar.MINUTE, minuto );

        return calendar.getTime();
    }

    public static Date subtrairDias( Date dataParam, int diasParam ) {
        if ( dataParam == null ) {
            return null;
        }
        DateTime result = new DateTime( dataParam );
        result = result.minusDays( diasParam );
        return result.toDate();
    }

    public static Date somarDias( Date dataParam, int diasParam ) {
        if ( dataParam == null ) {
            return null;
        }
        DateTime result = new DateTime( dataParam );
        result = result.plusDays( diasParam );
        return result.toDate();
    }

    public static Date subtrairMeses( Date dataParam, int mesesParam ) {
        if ( dataParam == null ) {
            return null;
        }
        DateTime result = new DateTime( dataParam );
        result = result.minusMonths( mesesParam );
        return result.toDate();
    }

    public static Date somarMeses( Date dataParam, int mesesParam ) {
        if ( dataParam == null ) {
            return null;
        }
        DateTime result = new DateTime( dataParam );
        result = result.plusMonths( mesesParam );
        return result.toDate();
    }

    public static Date obterPrimeiroDiaDoMes( Date dataParam ) {
        if ( dataParam == null ) {
            return null;
        }
        DateTime result = new DateTime( dataParam );
        result = result.withDayOfMonth( 1 );
        return result.toDate();
    }

    public static Date obterUltimoDiaDoMes( Date dataParam ) {
        if ( dataParam == null ) {
            return null;
        }
        DateTime result = new DateTime( dataParam );
        result = result.plusMonths( 1 );
        result = result.withDayOfMonth( 1 );
        result = result.minusDays( 1 );
        return result.toDate();
    }

    public static boolean verificarHorarioDeVerao( Date dataParam ) {
        DateTimeZone zone = DateTimeZone.forID( "America/Sao_Paulo" );
        return !zone.isStandardOffset( dataParam.getTime() );
    }

    public static Date getDataUltimaHoraUltimoDiaMes( Date dataFim ) {
        DateTime dtUltimoDiaDoMes = new DateTime( obterUltimoDiaDoMes( dataFim ) );
        dtUltimoDiaDoMes = dtUltimoDiaDoMes.plusDays( 1 ).withTime( 0, 0, 0, 0 ).minusSeconds( 1 );
        Date dataUltimoDiaDoMes = dtUltimoDiaDoMes.toDate();
        return dataUltimoDiaDoMes;
    }
    

    public static Map<String, Date> atualizaPeriodo( String opcaoPeriodoParam ) {
        Map<String, Date> periodo = new HashMap<String, Date>();
        DateTime dtIni = new DateTime();
        if ( opcaoPeriodoParam.indexOf( "mes" ) >= 0 ) {
            dtIni = dtIni.withDayOfMonth( 1 );
        }
        DateTime dtFinal = new DateTime();
        if ( DateUtil.TXT_MES_ANT.equals( opcaoPeriodoParam ) ) {
            dtIni = dtIni.minusMonths( 1 );
        } else if ( DateUtil.TXT_MES_POS.equals( opcaoPeriodoParam ) ) {
            dtIni = dtIni.plusMonths( 1 );
        } else if ( DateUtil.TXT_ONTEM.equals( opcaoPeriodoParam ) ) {
            dtIni = dtIni.minusDays( 1 );
        } else if ( DateUtil.TXT_AMANHA.equals( opcaoPeriodoParam ) ) {
            dtIni = dtIni.plusDays( 1 );
        }
        if ( DateUtil.TXT_ONTEM.equals( opcaoPeriodoParam ) || DateUtil.TXT_HOJE.equals( opcaoPeriodoParam ) || DateUtil.TXT_AMANHA.equals( opcaoPeriodoParam ) ) {
            dtFinal = new DateTime( dtIni );
        } else {
            dtFinal = dtIni.plusMonths( 1 ).withDayOfMonth( 1 ).minusDays( 1 );
        }
        dtIni = dtIni.withTime( 0, 0, 0, 0 );
        dtFinal = dtFinal.withTime( 0, 0, 0, 0 );
        periodo.put( DateUtil.TXT_DATA_INICIO, dtIni.toDate() );
        periodo.put( DateUtil.TXT_DATA_FIM, dtFinal.toDate() );
        return periodo;
    }

}
