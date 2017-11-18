package br.com.mobiew.siseve.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mobiew.siseve.exceptions.SistemaException;
import br.com.mobiew.siseve.model.entity.Evento;
import br.com.mobiew.siseve.service.EventoService;

@Component
public class EventoConverter implements Converter {

	@Autowired
    private EventoService eventoService;

    public Object getAsObject( FacesContext context, UIComponent component, String value ) {
        if ( value == null || value.trim().length() <= 0 ) {
            return null;
        }
        try {
            Evento evento = this.eventoService.findById( Long.parseLong( value ) );
            return evento;
        } catch ( NumberFormatException e ) {
            throw new ConverterException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro na conversão do objeto Evento", "Evento inválido." ), e );
        } catch ( Exception e ) {
            throw new SistemaException( "Erro não esperado na tentativa de conversão do objeto Evento - " + e.getMessage() );
        }
    }

	@Override
    public String getAsString( FacesContext context, UIComponent component, Object value ) {
        if ( value == null ) {
            return null;
        }
        return String.valueOf( ( (Evento) value ).getId() );
    }

}

