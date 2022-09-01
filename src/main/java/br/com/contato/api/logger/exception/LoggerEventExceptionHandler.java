package br.com.contato.api.logger.exception;

import br.com.contato.api.logger.Logger;
import br.com.contato.api.logger.model.Event;
import net.logstash.logback.encoder.com.lmax.disruptor.ExceptionHandler;

public class LoggerEventExceptionHandler implements ExceptionHandler<Event> {
	
	public LoggerEventExceptionHandler() {
		
	}

	@Override
	public void handleEventException(Throwable ex, final long sequence, final Event event) {
		Logger.error((String) null, ex.getMessage(), (Object) null, ex);
	}

	@Override
	public void handleOnShutdownException(final Throwable ex) {
		Logger.error((String) null, ex.getMessage(), (Object) null, ex);
	}

	@Override
	public void handleOnStartException(final Throwable ex) {
		Logger.error((String) null, ex.getMessage(), (Object) null, ex);
	}
	
	

}
