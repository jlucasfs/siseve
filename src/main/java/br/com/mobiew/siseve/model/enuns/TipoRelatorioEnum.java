package br.com.mobiew.siseve.model.enuns;

public enum TipoRelatorioEnum {
	MEDICO( "M" ), 
	OUTROS( "O" );

	private final String codigo;

	TipoRelatorioEnum( final String codigoParam ) {
		this.codigo = codigoParam;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public static TipoRelatorioEnum getEnumFromValue( final String value ) {
		for ( TipoRelatorioEnum enun: TipoRelatorioEnum.values() ) {
			if ( enun.getCodigo().equals( value ) ) {
				return enun;
			}
		}
		return null;
	}
}
