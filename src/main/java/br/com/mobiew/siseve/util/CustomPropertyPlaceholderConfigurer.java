package br.com.mobiew.siseve.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private Properties props;

	protected void loadProperties( Properties propsParam ) throws IOException {
		super.loadProperties( propsParam );
		this.props = propsParam;
	}

	public Properties getProps() {
		return props;
	}
}
