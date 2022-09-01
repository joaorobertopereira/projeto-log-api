package br.com.contato.api.logger.model;

import br.com.contato.api.logger.enums.Severity;

public class Event {
	
	private Severity severity;
	private Log value;
	
	public Event() {}
	
	public Log getValue() { return this.value; }
	
	public Severity getSeverity() { return this.severity; }
	
	public void setSeverity(final Severity severity) { this.severity = severity; }
	
	public void setValue(final Log value) { this.value = value; }
	
	public void clear() {
		this.value = null;
		this.severity = null;
	}

}
