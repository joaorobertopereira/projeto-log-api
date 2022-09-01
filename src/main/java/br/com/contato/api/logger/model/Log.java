package br.com.contato.api.logger.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

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


	public String getApplicationName() { return applicationName; }

	public void setApplicationName(String applicationName) { this.applicationName = applicationName; }

	public String getChannel() { return channel; }

	public void setChannel(String channel) { this.channel = channel; }

	public String getCodeLine() { return codeLine; }

	public void setCodeLine(String codeLine) { this.codeLine = codeLine; }

	public String getCorrelationId() { return correlationId; }

	public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }

	public String getDomain() { return domain; }

	public void setDomain(String domain) { this.domain = domain; }

	public String getInstanceId() { return instanceId; }

	public void setInstanceId(String instanceId) { this.instanceId = instanceId; }

	public String getLogCode() { return logCode; }

	public void setLogCode(String logCode) { this.logCode = logCode;	}

	public String getLogMessage() {	return logMessage; }

	public void setLogMessage(String logMessage) { this.logMessage = logMessage; }

	public Object getPayload() { return payload;	}

	public void setPayload(Object payload) { this.payload = payload; }

	public String getServiceId() { return serviceId; }

	public void setServiceId(String serviceId) { this.serviceId = serviceId;	}

	public String getSeverity() { return severity; }

	public void setSeverity(String severity) { this.severity = severity; }

	public String getThrownException() { return thrownException;	}

	public void setThrownException(String thrownException) { this.thrownException = thrownException; }

}
