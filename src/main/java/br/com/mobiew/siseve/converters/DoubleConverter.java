package br.com.mobiew.siseve.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.mobiew.siseve.util.ConstantesNumero;

@FacesConverter( value="doubleconv" )
public class DoubleConverter implements Converter {

    public Object getAsObject( FacesContext facesContext, UIComponent component, String submittedValue ) {
        if ( submittedValue == null || "".equals( submittedValue.trim() ) ) {
            return null;
        }
        String sub = submittedValue.replaceAll( "\\.", "" );
        sub = sub.replaceAll( ",", "." );
        return Double.valueOf( sub );
    }

    public String getAsString( FacesContext facesContext, UIComponent component, Object value ) {
        if ( value == null || "".equals( value ) ) {
            return "";
        }
        return ConstantesNumero.FMT_NUM_DECIMAL_2.format( value );
    }
}
