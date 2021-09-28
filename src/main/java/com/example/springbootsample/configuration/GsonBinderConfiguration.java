package com.example.springbootsample.configuration;

import com.example.springbootsample.configuration.gson.GsonLocalDate;
import com.google.gson.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class GsonBinderConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("size: " + converters.size());
        System.out.println(converters);
        converters.add(0, customGsonHttpMessageConverter());
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    private GsonHttpMessageConverter customGsonHttpMessageConverter() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new GsonLocalDate()).create();
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);

        return converter;
    }

    @Bean
    GsonBuilder gsonBuilder() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class, new GsonLocalDate());
    }

    @Bean
    Gson gson() {
        return gsonBuilder().create();
    }
}

