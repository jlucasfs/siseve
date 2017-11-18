package br.com.mobiew.siseve.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import br.com.mobiew.siseve.util.scopes.Scopes;

@Component
@Scope(Scopes.SINGLETON)
public class MessagesHelper {

	private ResourceBundle messages;

	@Autowired
	ReloadableResourceBundleMessageSource messageSource;

	public void addMessage(Severity severity, String summaryKey,
			String detailKey) {
		addMessage(null, severity, summaryKey, detailKey);
	}

	public void addMessage(String componentId, Severity severity,
			String summaryKey, String detailKey) {
		FacesContext.getCurrentInstance().addMessage(componentId,
				new FacesMessage(severity, summaryKey, detailKey));
	}

	public Boolean hasMessages() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		return facesContext.getMessages(null).hasNext()
				|| facesContext.getMessages().hasNext();
	}

	public MessagesHelper() {

	}

	public ResourceBundle getMessages() {
		if (messages == null) {
			messages = ResourceBundle.getBundle("messages");
		}

		return messages;
	}

	public String getKeyValueFromBundle(String key) {
		if (key == null) {
			return null;
		}

		String msg = null;

		try {
			msg = getMessages().getString(key);
			return msg;
		} catch (MissingResourceException e) {
			return "???" + key + "???";
		}
	}

	public void setMessages(ResourceBundle messages) {
		this.messages = messages;
	}

	public void addErrorMessage(String summaryKey, String detailKey) {
		addMessage(FacesMessage.SEVERITY_ERROR,
				getKeyValueFromBundle(summaryKey),
				getKeyValueFromBundle(detailKey));
	}
	
	public void addErrorMessage(String componentId, String summaryKey,
			String detailKey) {
		addMessage(componentId, FacesMessage.SEVERITY_ERROR,
				getKeyValueFromBundle(summaryKey),
				getKeyValueFromBundle(detailKey));
	}

	public void addInfoMessage(String summaryKey, String detailKey) {
		addMessage(FacesMessage.SEVERITY_INFO,
				getKeyValueFromBundle(summaryKey),
				getKeyValueFromBundle(detailKey));
	}

	public void addInfoMessage(String componentId, String summaryKey,
			String detailKey) {
		addMessage(componentId, FacesMessage.SEVERITY_INFO,
				getKeyValueFromBundle(summaryKey),
				getKeyValueFromBundle(detailKey));
	}

	public void addWarnMessage(String summaryKey, String detailKey) {
		addMessage(FacesMessage.SEVERITY_WARN,
				getKeyValueFromBundle(summaryKey),
				getKeyValueFromBundle(detailKey));
	}

	public void addErrorMessage(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		if (violations != null) {
			for (ConstraintViolation<?> v : violations) {
				addErrorMessage(
						v.getMessageTemplate().substring(1,
								v.getMessageTemplate().length() - 1), null);
			}
		}
	}
}
