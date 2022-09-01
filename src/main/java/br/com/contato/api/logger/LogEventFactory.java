package br.com.contato.api.logger;

import br.com.contato.api.logger.model.Event;
import net.logstash.logback.encoder.com.lmax.disruptor.EventFactory;

public class LogEventFactory implements EventFactory<Event> {

	public LogEventFactory() {
		
	}
	
	@Override
	public Event newInstance() {
		return new Event();
	}
	

}
