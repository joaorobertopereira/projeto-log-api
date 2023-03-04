package br.com.contato.api.utils.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.regex.Pattern;

public class MaskDataSerializer extends StdSerializer<Object> {
    private static final long serialVersionUID = 0L;

    public MaskDataSerializer() {
        super(Object.class);
    }

    @Override
    public void serialize(final Object o, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(Pattern.compile(".").matcher(o.toString()).replaceAll("#"));
    }

}
