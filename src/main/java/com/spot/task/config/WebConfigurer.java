package com.spot.task.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class WebConfigurer {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
