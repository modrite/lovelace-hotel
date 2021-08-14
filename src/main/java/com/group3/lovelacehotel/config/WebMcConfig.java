package com.group3.lovelacehotel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Component
public class WebMcConfig implements WebMvcConfigurer {
    private final HandleInterceptor handleInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handleInterceptor);
    }
}
