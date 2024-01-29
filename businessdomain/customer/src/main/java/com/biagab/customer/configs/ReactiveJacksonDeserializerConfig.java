package com.biagab.customer.configs;

import com.biagab.customer.models.Product;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonComponent;
import reactor.core.publisher.Mono;

import java.io.IOException;

@JsonComponent
public class ReactiveJacksonDeserializerConfig extends JsonDeserializer<Mono<Product>> {

    @Override
    public Mono<Product> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Product product = new Product();
        // Aquí debes realizar la deserialización del nodo JSON en un objeto Product
        // por ejemplo, si Product tiene un constructor que acepta un JsonNode
        // product.deserialize(node);

        // Luego, debes envolver el objeto Product en un Mono
        return Mono.just(product);
    }
}
