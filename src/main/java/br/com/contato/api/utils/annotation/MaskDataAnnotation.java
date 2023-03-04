package br.com.contato.api.utils.annotation;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.util.Objects;

public class MaskDataAnnotation extends JacksonAnnotationIntrospector {
    private static final long serialVersionUID = 0L;

    public MaskDataAnnotation() {

    }

    public Object findSerializer(final Annotated am) {
        return Objects.nonNull(am.getAnnotation(Mask.class)) ? MaskDataSerializer.class : super.findSerializer(am);
    }

    public Version version() {
        return new Version(1,0,0,"RELEASE", "br.com.contato.api", "lib-log");
    }
}
