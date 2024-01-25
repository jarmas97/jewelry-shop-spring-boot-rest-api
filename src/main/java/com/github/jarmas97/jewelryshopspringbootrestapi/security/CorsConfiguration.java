package com.github.jarmas97.jewelryshopspringbootrestapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("https://sevenjewellery.pl")                      //production
                .allowedOrigins("*")           //development
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
