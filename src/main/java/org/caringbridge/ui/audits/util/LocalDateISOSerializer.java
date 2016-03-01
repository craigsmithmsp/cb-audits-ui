package org.caringbridge.ui.audits.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateISOSerializer extends StdSerializer<LocalDateTime> {

    /**
     * 
     */
    private static final long serialVersionUID = -2374036968445239176L;

    
    protected LocalDateISOSerializer() {
        super(LocalDateTime.class);
    }
    
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null){
            gen.writeString(value.format(DateTimeFormatter.ISO_DATE_TIME));
        }else{
            gen.writeNull();
        }
    }

}
