package com.condoapp.bloc.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private String 
}
