package br.com.contato.api.logger.exception;

import br.com.contato.api.logger.model.Event;
import lombok.NoArgsConstructor;
import net.logstash.logback.encoder.com.lmax.disruptor.EventHandler;

@NoArgsConstructor
public class ClearEventHandler implements EventHandler<Event> {
	
	@Override
	public void onEvent(final Event event,final long sequence,final boolean endOfBatch) throws Exception {
		event.clear();
		
	}

}
