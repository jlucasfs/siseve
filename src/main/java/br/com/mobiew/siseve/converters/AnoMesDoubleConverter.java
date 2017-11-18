package br.com.mobiew.siseve.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter( value="anoMesDoubleConv" )
public class AnoMesDoubleConverter implements Converter {

    public Object getAsObject( FacesContext facesContext, UIComponent component, String submittedValue ) {
        if ( submittedValue == null || "".equals( submittedValue.trim() ) ) {
            return null;
        }
        return submittedValue;
    }

    public String getAsString( FacesContext facesContext, UIComponent component, Object value ) {
        if ( value == null || value.equals( "" ) ) {
            return "";
        }
        Double d = (Double) value;  
        Long s = Math.round (d * 3600);  
        Long h = s / 3600;  
        Long m = (s - 3600 * h) / 60;  
        return "" + ((h < 10) ? "0"+h : h) + " : " + ((m < 10) ? "0"+m : m);
    }
}
