package com.kirin.demo.config;

import com.kirin.demo.aspects.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Настройки конфигурации
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Регистрирует перехватчик HTTP-запросов (LoggingInterceptor).
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }

}
