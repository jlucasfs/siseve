package br.com.mobiew.siseve.util.exceptions;

public class ApplicationIllegalArgumentDaoException extends ApplicationDaoException {

    private static final long serialVersionUID = 1L;

    public ApplicationIllegalArgumentDaoException( Throwable e ) {
        super( e );
    }

    public ApplicationIllegalArgumentDaoException( String msg, Throwable e, Long cod ) {
        super( msg, e );
        if ( cod != null ) {
			cod.getClass();
		}
    }

    public ApplicationIllegalArgumentDaoException( String msg ) {
        super( msg );
    }

    public ApplicationIllegalArgumentDaoException( String msg, Throwable e ) {
        super( msg, e );
    }

    public ApplicationIllegalArgumentDaoException( String msg, Long cod ) {
        super( msg, cod );
    }

    public ApplicationIllegalArgumentDaoException( ApplicationIllegalArgumentUtilException e ) {
        super( e );
    }

}
