package br.com.mobiew.siseve.model.enuns;

public enum DiaSemanaEnum {

	DOM("Domingo"),SEG("Segunda"), TER("Terça"),QUA("Quarta"),QUI("Quinta"),SEX("Sexta"),SAB("Sábado");

	private final String valor;

	private DiaSemanaEnum(final String valor) {
		this.valor = valor;
	}
	
	public String getValor() {		
		return valor;
	}
}
