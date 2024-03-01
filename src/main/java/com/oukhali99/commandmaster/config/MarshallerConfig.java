package com.oukhali99.commandmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class MarshallerConfig {

    @Bean
    public Marshaller marshaller() {
        return new XStreamMarshaller();
    }

    @Bean
    public Unmarshaller unmarshaller() {
        return new XStreamMarshaller();
    }

}
