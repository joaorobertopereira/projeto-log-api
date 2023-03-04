package br.com.contato.api.logger.exception;

import br.com.contato.api.logger.model.Event;
import br.com.contato.api.utils.annotation.MaskDataAnnotation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import net.logstash.logback.encoder.com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;

public class LoggerEventHandler implements EventHandler<Event> {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private final Logger logger;
	
	public LoggerEventHandler(final Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public void onEvent(final Event event, final long sequence, final boolean endOfBatch) throws Exception {
		String writeValueAsString = MAPPER.writeValueAsString(event.getValue());
		
		switch(event.getSeverity()) {
		case ERROR:
			this.logger.error(writeValueAsString);
			break;
		case WARN:
			this.logger.warn(writeValueAsString);
			break;
		case INFO:
			this.logger.info(writeValueAsString);
			break;
		case DEBUG:
			this.logger.debug(writeValueAsString);
			break;
		case TRACE:
			this.logger.trace(writeValueAsString);
		}
		
	}

	static {
		MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MAPPER.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
		MAPPER.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
		MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		MAPPER.setAnnotationIntrospector(new MaskDataAnnotation());


	}


}
