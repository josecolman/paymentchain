package com.biagab.customer.configs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;
import java.io.IOException;

@Configuration
public class ReactiveJacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        /*SimpleModule module = new SimpleModule();

        module.addSerializer(Mono.class, new JsonSerializer<Mono>() {
            @Override
            public void serialize(Mono mono, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                // Obtén el valor del Mono y escríbelo en el generador JSON
                Object valor = mono.block(); // Esto bloqueará el hilo actual, asegúrate de que esté bien en tu contexto
                jsonGenerator.writeObject(valor);
            }
        });


        objectMapper.registerModule(module);*/

        return objectMapper;
    }

    /*@Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        TypeReference<ParameterizedTypeReference<Mono<?>>> typeRef = new TypeReference<>() {
        };

        //Class<? extends T> rawType = (Class<? extends T>) typeRef.getType();

        module.addSerializer(Mono<? extends Mono<?>>, new JsonSerializer<Mono<?>>() {


            @Override
            public void serialize(Mono<?> mono, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

            }
        });
        objectMapper.registerModule(module);
        return objectMapper;
    }*/
}
