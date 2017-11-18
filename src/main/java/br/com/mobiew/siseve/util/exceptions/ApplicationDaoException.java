package br.com.mobiew.siseve.util.exceptions;

public class ApplicationDaoException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ApplicationDaoException( Throwable e ) {
        super( e );
    }

    public ApplicationDaoException( String msg, Throwable e ) {
        super( msg, e );
    }

    public ApplicationDaoException( String msg ) {
        super( msg );
    }

    public ApplicationDaoException( String msg, Long cod ) {
        super( msg, cod );
    }

    public ApplicationDaoException( String msg, Throwable e, Long cod ) {
        super( msg, e, cod );
    }

}
