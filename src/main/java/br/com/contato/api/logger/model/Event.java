package br.com.contato.api.logger.model;

import br.com.contato.api.logger.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	
	private Severity severity;
	private Log value;

	public void clear() {
		this.value = null;
		this.severity = null;
	}

}
