package org.caringbridge.ui.audits.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateISODeserializer extends StdDeserializer<LocalDateTime> {

    /**
     * 
     */
    private static final long serialVersionUID = -2374036968445239176L;

    
    protected LocalDateISODeserializer() {
        super(LocalDate.class);
    }


    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        if (!p.hasToken(JsonToken.VALUE_STRING)){
            ctxt.wrongTokenException(p, JsonToken.VALUE_STRING, "Expected String Token with ISO Formatted Date");
        }
        
        String v = p.getValueAsString();
        LocalDateTime ld = null;
        try{
             ld = LocalDateTime.parse(v, DateTimeFormatter.ISO_DATE_TIME);
        }catch(Exception e){
            ctxt.weirdStringException(v, String.class, "Value of date can't be parsed using ISO format");
        }
        return ld;
    }
    
}
