package com.example.demo.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.stereotype.Component;

@Component
public class Initializer extends AbstractHttpSessionApplicationInitializer {

    public Initializer() {
        super(WebAppConfig.class);
    }

}
