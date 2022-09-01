package br.com.contato.api.logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import br.com.contato.api.logger.model.Event;
import br.com.contato.api.logger.model.Log;
import org.slf4j.MDC;

import br.com.contato.api.logger.exception.ClearEventHandler;
import br.com.contato.api.logger.exception.LoggerEventExceptionHandler;
import br.com.contato.api.logger.exception.LoggerEventHandler;
import br.com.contato.api.logger.enums.DaemonThreadFactory;
import br.com.contato.api.logger.enums.Severity;

import org.slf4j.LoggerFactory;

import net.logstash.logback.encoder.com.lmax.disruptor.BlockingWaitStrategy;
import net.logstash.logback.encoder.com.lmax.disruptor.EventHandler;
import net.logstash.logback.encoder.com.lmax.disruptor.dsl.Disruptor;
import net.logstash.logback.encoder.com.lmax.disruptor.dsl.ProducerType;
import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class Logger {
	
	private static String applicationName;
	private static String serviceId;
	private static String domain;
	private static Optional<String> instanceId;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Logger.class);
	private static final Disruptor<Event> DISRUPTOR;
	private static final LoggerEventProducer LOGGER_EVENT_PRODUCER;
	
	private Logger() {
		
	}
	
	public static void error(final String logCode, final String message, final Object payload) {
		if(LOGGER.isErrorEnabled()) {
			doLog(Severity.ERROR, getCorrelationId(), logCode, message, getCaller(), Optional.ofNullable(getChannel()), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void error(final String logCode, final String message, final Object payload, final Throwable exception) {
		if(LOGGER.isErrorEnabled()) {
			doLog(Severity.ERROR, getCorrelationId(), logCode, message, getCaller(), Optional.ofNullable(getChannel()), Optional.ofNullable(payload), Optional.ofNullable(exception));
		}
	}

	public static void warning(final String logCode, final String message, final Object payload) {
		if(LOGGER.isWarnEnabled()) {
			doLog(Severity.WARN, getCorrelationId(), logCode, message, getCaller(), Optional.ofNullable(getChannel()), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void info(final String logCode, final String message, final Object payload) {
		if(LOGGER.isInfoEnabled()) {
			doLog(Severity.INFO, getCorrelationId(), logCode, message, getCaller(), Optional.ofNullable(getChannel()), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void debug(final String logCode, final String message, final Object payload) {
		if(LOGGER.isDebugEnabled()) {
			doLog(Severity.DEBUG, getCorrelationId(), logCode, message, getCaller(), Optional.ofNullable(getChannel()), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void trace(final String logCode, final String message, final Object payload) {
		if(LOGGER.isTraceEnabled()) {
			doLog(Severity.TRACE, getCorrelationId(), logCode, message, getCaller(), Optional.ofNullable(getChannel()), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void doLog(final Severity severity, final String correlationId, final String logCode, final String message, final String codeLine, final Optional<String> channel, final Optional<Object> payload, final Optional<Throwable> throwable ) {
		Log log = new Log();
		log.setApplicationName(applicationName);
		log.setCorrelationId(correlationId);
		log.setDomain(domain);
		log.setServiceId(serviceId);
		log.setLogCode(logCode);
		log.setLogMessage(message);
		log.setSeverity(severity.getValue());
		log.setCodeLine(codeLine);
		channel.ifPresent(log::setChannel);
		throwable.ifPresent((e) -> {
			log.setThrownException(ExceptionUtils.getStackTrace(e));
		});
		instanceId.ifPresent(log::setInstanceId);
		payload.ifPresent(log::setPayload);
		LOGGER_EVENT_PRODUCER.onData(log, severity);
	}

	public static String getCorrelationId() { return MDC.get("correlationId"); }

	public static String getChannel() { return MDC.get("channel"); }
	
	private static String getCaller() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
		return stackTraceElement.getClassName().concat(" : ").concat(String.valueOf(stackTraceElement.getLineNumber()));
	}
	
	public static void setCorrelationId(final String correlationId) { MDC.put("correlationId", correlationId); }
	
	public static void setChannel(final String channel) { MDC.put("channel", channel); }
	
	public static void setApplicationName(final String applicationName) { Logger.applicationName = applicationName; }
	
	public static void setServiceId(final String serviceId) { Logger.serviceId = serviceId; }
	
	public static void setDomain(final String domain) { Logger.domain = domain; }
	
	static {
		DISRUPTOR = new Disruptor(new LogEventFactory(), 2048, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());
		try {
			instanceId = Optional.ofNullable(InetAddress.getLocalHost().getCanonicalHostName());
			
		} catch (UnknownHostException var1) {
			instanceId = Optional.empty();
		}
		
		DISRUPTOR.setDefaultExceptionHandler(new LoggerEventExceptionHandler());
		DISRUPTOR.handleEventsWith(new EventHandler[] {new LoggerEventHandler(LOGGER)}).then(new EventHandler[] {new ClearEventHandler()});
		DISRUPTOR.start();
		LOGGER_EVENT_PRODUCER = new LoggerEventProducer(DISRUPTOR.getRingBuffer());
	}
	
	
	
	
	
}
