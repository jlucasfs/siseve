package br.com.mobiew.siseve.filters;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AuthorizationListener implements PhaseListener {

    private static final long serialVersionUID = -3038484539620514546L;

    public void afterPhase( PhaseEvent event ) {
//        FacesContext facesContext = event.getFacesContext();
//        String currentPage = facesContext.getViewRoot().getViewId();
//        boolean isLoginPage = ( currentPage.lastIndexOf( "login." ) > -1 );
//        Object currentUser = Util.obterAtributoSessao( Constantes.USUARIO_AUTENTICADO );
//        if ( !isLoginPage && currentUser == null ) {
//            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//            nh.handleNavigation( facesContext, null, "login?faces-redirect=true" );
//        }
    }

    public void beforePhase( PhaseEvent event ) {
    	//
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
