package br.com.mobiew.siseve.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {

    public static void redirect( String path ) {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.sendRedirect( path );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static String getCRUDPath() {
        return "/pages/crud/";
    }

    public static String getContextPath() {
        FacesContext context = getInstance();
        String path = context.getExternalContext().getRequestContextPath();
        return path;
    }

    public static FacesContext getInstance() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static String getParameter( String name ) {
        return getRequest().getParameter( name );
    }

    public static void addFatalMessage( String componente, String message ) {
        addKeyMessage( componente, message, FacesMessage.SEVERITY_FATAL );
    }

    public static void addWarnMessage( String componente, String message ) {
        addKeyMessage( componente, message, FacesMessage.SEVERITY_WARN );
    }

    public static void addInfoMessage( String componente, String message ) {
        addKeyMessage( componente, message, FacesMessage.SEVERITY_INFO );
    }

    public static void addErrorMessage( String componente, String message ) {
        addKeyMessage( componente, message, FacesMessage.SEVERITY_ERROR );
    }

    public static void addKeyMessage( String componente, String message, Severity sev ) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent uic = null;
        if ( componente != null ) {
            uic = fc.getViewRoot().findComponent( componente );
        }
        String dest = null;
        if ( uic != null ) {
            dest = uic.getClientId( fc );
        }
        fc.addMessage( dest, new FacesMessage( sev, message, message ) );
    }

    /**
     * Limpa as mensagens globais
     */
    public static void clearMessages() {
        Iterator<FacesMessage> msgIterator = getInstance().getMessages();
        while ( msgIterator.hasNext() ) {
            msgIterator.next();
            msgIterator.remove();
        }
    }
}
