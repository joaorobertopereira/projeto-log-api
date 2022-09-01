package br.com.contato.api.logger;

import br.com.contato.api.logger.enums.Severity;
import br.com.contato.api.logger.model.Event;
import br.com.contato.api.logger.model.Log;
import net.logstash.logback.encoder.com.lmax.disruptor.EventTranslatorTwoArg;
import net.logstash.logback.encoder.com.lmax.disruptor.RingBuffer;

public class LoggerEventProducer {
	private final RingBuffer<Event> ringBuffer;
	private static final EventTranslatorTwoArg<Event, Log, Severity> TRANSLATOR = (event, sequence, log, severity) -> {
		event.setValue(log);
		event.setSeverity(severity);
	};
	
	public LoggerEventProducer(final RingBuffer<Event> ringBuffer) { this.ringBuffer = ringBuffer; }
	
	public void onData(final Log log, final Severity severity) {
		this.ringBuffer.publishEvent(TRANSLATOR, log, severity);
	}
	

}
