package br.com.contato.api.logger.enums;

public enum Severity {
	ERROR("ERROR"),
	WARN("WARNING"),
	INFO("INFO"),
	DEBUG("DEBUG"),
	TRACE("TRACE");
	
	String value;
	private Severity() {}
	private Severity(final String value) {this.value = value;}
	public String getValue() { return this.value; }
}
