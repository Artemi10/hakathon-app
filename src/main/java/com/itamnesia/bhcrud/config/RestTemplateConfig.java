package com.itamnesia.bhcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        var jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));

        var xmlConverter = new Jaxb2RootElementHttpMessageConverter();
        xmlConverter.setSupportedMediaTypes(List.of(MediaType.TEXT_XML, MediaType.APPLICATION_XML));

        var formConverter = new FormHttpMessageConverter();
        formConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_FORM_URLENCODED));

        var restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(List.of(jsonConverter, xmlConverter, formConverter));
        return restTemplate;
    }
}
