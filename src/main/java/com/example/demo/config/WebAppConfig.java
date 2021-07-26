package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.util.HttpSessionMutexListener;

@Configuration
@EnableRedisHttpSession
public class WebAppConfig {
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionMutexListener> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<HttpSessionMutexListener> listenerRegBean =
                new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new HttpSessionMutexListener());
        return listenerRegBean;
    }
}
