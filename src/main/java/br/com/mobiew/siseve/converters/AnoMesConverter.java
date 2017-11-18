package br.com.mobiew.siseve.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

public class AnoMesConverter implements Converter {

    public Object getAsObject( FacesContext facesContext, UIComponent component, String submittedValue ) {
        if ( submittedValue == null || "".equals( submittedValue.trim() ) ) {
            return null;
        }
        String[] mesano = submittedValue.split( "/" );
        if ( mesano.length != 2 ) {
            return null;
        }
        String anomes = mesano[1] + mesano[0];
        return anomes;
    }

    public String getAsString( FacesContext facesContext, UIComponent component, Object value ) {
        if ( value == null || value.equals( "" ) || StringUtils.length( (String) value ) != 6 ) {
            return "";
        }
        String anomes = (String) value;
        String mesano = anomes.substring( 4, 6 ) + "/" + anomes.substring( 0, 4 );
        return mesano;
    }
}
