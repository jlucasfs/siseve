package br.com.mobiew.siseve.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class DeleteStringMaskConverter implements Converter {

    public Object getAsObject( FacesContext facesContext, UIComponent component, String submittedValue ) {
        if ( submittedValue == null || "".equals( submittedValue.trim() ) ) {
            return null;
        }
        return submittedValue.replaceAll( "\\.", "" ).replaceAll( "-", "" ).replaceAll( "/", "" ).replaceAll( "\\(", "" ).replaceAll( "\\)", "" ).replaceAll( ":", "" );
    }

    public String getAsString( FacesContext facesContext, UIComponent component, Object value ) {
        if (value == null || value.equals("")) {
            return "";
        }
        return (String) value;
    }
}
