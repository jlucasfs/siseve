package br.com.mobiew.siseve.util.exceptions;

import java.util.StringTokenizer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LogManager.getLogger( ApplicationException.class );

	public ApplicationException( Throwable e ) {
		super( e );
		LOG.error( e );
	}

	public ApplicationException( String msg ) {
		super( msg );
		LOG.error( msg );
	}

	public ApplicationException( String msg, Throwable e ) {
		super( msg, e );
		LOG.error( msg, e );
	}

	public static String getClassName( Integer deep ) {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();

		return ste[deep].getClassName();
	}

	public static String getFileName( Integer deep ) {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();
		return ste[deep].getFileName();
	}

	public static String getMethodName( Integer deep ) {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();
		return ste[deep].getMethodName();
	}

	public static Integer getLineNumber( Integer deep ) {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();
		return ste[deep].getLineNumber();
	}

	public static String getReference( Integer deep ) {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();
		return ste[deep].toString();
	}

	public String getMessage() {
		String message = super.getMessage();
		if ( message != null && message.trim().startsWith( ";" ) ) {
			StringTokenizer tokenizer = new StringTokenizer( message, ":" );
			StringBuffer buffer = new StringBuffer();
			while ( tokenizer.hasMoreTokens() ) {
				buffer.setLength( 0 );
				buffer.append( tokenizer.nextToken() );
			}
			return buffer.toString();
		} else if ( message != null ) {
			return message;
		}

		return null;
	}

	public ApplicationException( String msg, Long cod ) {
		super( msg );
		if ( cod != null ) {
			cod.getClass();
		}
	}

	public ApplicationException( int httpStatus, String msg ) {
		super( msg );
		Integer hs = Integer.valueOf( httpStatus );
		hs.getClass();
	}

	public ApplicationException( String msg, Throwable e, Long cod ) {
		super( msg, e );
		if ( cod != null ) {
			cod.getClass();
		}
	}

}
