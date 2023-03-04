package br.com.contato.api.logger;

import br.com.contato.api.logger.enums.DaemonThreadFactory;
import br.com.contato.api.logger.enums.Severity;
import br.com.contato.api.logger.exception.ClearEventHandler;
import br.com.contato.api.logger.exception.LoggerEventExceptionHandler;
import br.com.contato.api.logger.exception.LoggerEventHandler;
import br.com.contato.api.logger.model.Event;
import br.com.contato.api.logger.model.Log;
import net.logstash.logback.encoder.com.lmax.disruptor.BlockingWaitStrategy;
import net.logstash.logback.encoder.com.lmax.disruptor.EventHandler;
import net.logstash.logback.encoder.com.lmax.disruptor.dsl.Disruptor;
import net.logstash.logback.encoder.com.lmax.disruptor.dsl.ProducerType;
import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class Logger {

	private static Optional<String> instanceId;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Logger.class);
	private static final Disruptor<Event> DISRUPTOR;
	private static final LoggerEventProducer LOGGER_EVENT_PRODUCER;
	
	private Logger() {
		
	}
	
	public static void error(final String logCode, final String message, final Object payload) {
		if(LOGGER.isErrorEnabled()) {
			doLog(Severity.ERROR, logCode, message, getCaller(), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void error(final String logCode, final String message, final Object payload, final Throwable exception) {
		if(LOGGER.isErrorEnabled()) {
			doLog(Severity.ERROR, logCode, message, getCaller(), Optional.ofNullable(payload), Optional.ofNullable(exception));
		}
	}

	public static void warning(final String logCode, final String message, final Object payload) {
		if(LOGGER.isWarnEnabled()) {
			doLog(Severity.WARN, logCode, message, getCaller(), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void info(final String logCode, final String message, final Object payload) {
		if(LOGGER.isInfoEnabled()) {
			doLog(Severity.INFO, logCode, message, getCaller(), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void debug(final String logCode, final String message, final Object payload) {
		if(LOGGER.isDebugEnabled()) {
			doLog(Severity.DEBUG, logCode, message, getCaller(),  Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void trace(final String logCode, final String message, final Object payload) {
		if(LOGGER.isTraceEnabled()) {
			doLog(Severity.TRACE, logCode, message, getCaller(), Optional.ofNullable(payload), Optional.empty());
		}
	}

	public static void doLog(final Severity severity, final String logCode, final String message, final String codeLine, final Optional<Object> payload, final Optional<Throwable> throwable ) {
		Log log = new Log();
		log.setLogCode(logCode);
		log.setLogMessage(message);
		log.setSeverity(severity.getValue());
		log.setCodeLine(codeLine);
		throwable.ifPresent((e) -> {
			log.setThrownException(ExceptionUtils.getStackTrace(e));
		});
		instanceId.ifPresent(log::setInstanceId);
		payload.ifPresent(log::setPayload);
		LOGGER_EVENT_PRODUCER.onData(log, severity);
	}

	private static String getCaller() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
		return stackTraceElement.getClassName().concat(" : ").concat(String.valueOf(stackTraceElement.getLineNumber()));
	}

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
