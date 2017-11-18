package br.com.mobiew.siseve.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.mobiew.siseve.model.enuns.SexoEnum;

//@FacesConverter( value="convsex" )
public class SexoConverter implements Converter {

    public Object getAsObject( FacesContext facesContext, UIComponent component, String submittedValue ) {
        if ( submittedValue == null || "".equals( submittedValue.trim() ) ) {
            return null;
        }
        if ( SexoEnum.M.name().equals( submittedValue ) ) {
            return SexoEnum.M.name();
        }
        if ( SexoEnum.F.name().equals( submittedValue ) ) {
            return SexoEnum.F.name();
        }
        return null;
    }

    public String getAsString( FacesContext facesContext, UIComponent component, Object value ) {
        if (value == null || value.equals("")) {
            return "";
        }
        return SexoEnum.valueOf( (String) value ).name();
    }
}
