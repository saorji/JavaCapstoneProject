package com.samuelhrm.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ModelMapperConfig {

    public class MapperConfig
    {
        @Bean
        protected ModelMapper createModelMapper()
        {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper;
        }
    }
}
