package com.sti.extractcontrolmodule.config;

//import org.springframework.context.annotation.Configuration;

import com.sti.extractcontrolmodule.utils.SortingPagingUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebMvcConfiguration {
    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("static/messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
    @Bean
    public SortingPagingUtils sortingPagingUtils(){
        return new SortingPagingUtils();
    }
}
