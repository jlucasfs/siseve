package br.com.mobiew.siseve.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.mobiew.siseve.model.enuns.SimNaoEnum;

public class CheckBoxConverter implements Converter {

    // private static final Logger LOG = Logger.getLogger( CheckBoxConverter.class );

    public Object getAsObject( final FacesContext facesContext, final UIComponent component, final String submittedValue ) {
        if ( submittedValue == null ) {
            return null;
        }
        String sn = Boolean.parseBoolean( submittedValue ) ? SimNaoEnum.S.name() : SimNaoEnum.N.name();
        if ( SimNaoEnum.S.name().equals( sn ) ) {
            return SimNaoEnum.S;
        }
        if ( SimNaoEnum.N.name().equals( sn ) ) {
            return SimNaoEnum.N;
        }
        return null;
    }

    public String getAsString( FacesContext facesContext, UIComponent component, Object value ) {
        if ( value == null || value.equals( "" ) ) {
            return Boolean.FALSE.toString();
        }
        return value.toString();
    }
}
