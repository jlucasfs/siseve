package br.com.mobiew.siseve.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mobiew.siseve.exceptions.SistemaException;
import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.service.ProfissionalService;

@Component
public class ProfissionalConverter implements Converter {

    @Autowired
    private ProfissionalService profissionalService;

    public Object getAsObject( FacesContext context, UIComponent component, String value ) {
        if ( value == null || value.trim().length() <= 0 ) {
            return null;
        }
        try {
            Profissional ent = this.profissionalService.findById( Long.parseLong( value ) );
            return ent;
        } catch ( NumberFormatException e ) {
            throw new ConverterException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro na conversão do objeto Profissional", "Profissional inválido." ), e );
        } catch ( Exception e ) {
            throw new SistemaException( "Erro não esperado na tentativa de conversão do objeto Profissional - " + e.getMessage() );
        }
    }

    @Override
    public String getAsString( FacesContext context, UIComponent component, Object value ) {
        if ( value == null ) {
            return null;
        }
        return String.valueOf( ( (Profissional) value ).getId() );
    }
}
