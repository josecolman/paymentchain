package com.biagab.customer;

import com.biagab.customer.models.Product;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class MonoDeserializer extends JsonDeserializer<Mono<?>> {

    private final ObjectMapper objectMapper;

    public MonoDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        // Deserializa el contenido del Mono a partir del JsonNode
        Object contenido = objectMapper.treeToValue(node, Object.class);
        // Crea un Mono con el contenido deserializado
        return Mono.just(contenido);
    }
}
