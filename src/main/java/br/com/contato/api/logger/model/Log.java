package br.com.contato.api.logger.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ApplicationName")
	private String applicationName;
	
	@JsonProperty("Channel")
	private String channel;
	
	@JsonProperty("CodeLine")
	private String codeLine;
	
	@JsonProperty("CorrelationId")
	private String correlationId;
	
	@JsonProperty("DateTime")
	private final String dateTime;
	
	@JsonProperty("Domain")
	private String domain;
	
	@JsonProperty("InstanceID")
	private String instanceId;
	
	@JsonProperty("LogCode")
	private String logCode;
	
	@JsonProperty("LogMessage")
	private String logMessage;

	@JsonProperty("Payload")
	private transient Object payload;
	
	@JsonProperty("ServiceId")
	private String serviceId;

	@JsonProperty("Severity")
	private String severity;
	
	@JsonProperty("Timestamp")
	private final String timestamp;

	@JsonProperty("ThrownException")
	private String thrownException;
	
	public Log() {
		LocalDateTime localDateTime = LocalDateTime.now();
		this.dateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS"));
		this.timestamp = String.valueOf(localDateTime.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
	}
}
