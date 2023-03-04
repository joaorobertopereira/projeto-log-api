package br.com.contato.api.logger.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.contato.api.utils.annotation.Mask;
import br.com.contato.api.utils.annotation.MaskDataAnnotation;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("LogCode")
	private String logCode;

	@JsonProperty("DateTime")
	private final String dateTime;

	@JsonProperty("InstanceID")
	private String instanceId;

	@JsonProperty("CodeLine")
	private String codeLine;

	@JsonProperty("LogMessage")
	private String logMessage;

	@JsonProperty("Payload")
	private transient Object payload;

	@JsonProperty("Severity")
	private String severity;

	@JsonProperty("ThrownException")
	private String thrownException;
	
	public Log() {
		LocalDateTime localDateTime = LocalDateTime.now();
		this.dateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
}
