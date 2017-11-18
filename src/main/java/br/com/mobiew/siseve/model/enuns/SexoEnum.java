package br.com.mobiew.siseve.model.enuns;

public enum SexoEnum {

	M("Masculino"), F("Feminino");

	private final String sexo;

	SexoEnum(final String sexoParam) {
		this.sexo = sexoParam;
	}

	public String getSexo() {
		return this.sexo;
	}

	public static SexoEnum getEnumFromValue(final String value) {
		for (SexoEnum sex : SexoEnum.values()) {
			if (sex.getSexo().equals(value)) {
				return sex;
			}
		}
		return null;
	}
	
	public String getKey() {
		return "sexo." + this.name();
	}

}
