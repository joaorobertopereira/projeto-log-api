package br.com.contato.api.logger.exception;

import br.com.contato.api.logger.model.Event;
import net.logstash.logback.encoder.com.lmax.disruptor.EventHandler;

public class ClearEventHandler implements EventHandler<Event> {

	public ClearEventHandler() {
		
	}
	
	@Override
	public void onEvent(final Event event,final long sequence,final boolean endOfBatch) throws Exception {
		event.clear();
		
	}

}
