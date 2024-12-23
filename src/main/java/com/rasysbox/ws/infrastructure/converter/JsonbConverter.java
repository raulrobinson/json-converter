package com.rasysbox.ws.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonbConverter implements AttributeConverter<String, String> {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JsonbConverter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException jpe) {
            log.warn("Cannot convert Address into JSON");
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }

}
