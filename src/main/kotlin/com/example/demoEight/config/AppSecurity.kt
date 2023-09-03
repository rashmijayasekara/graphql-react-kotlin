package com.example.demoEight.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class AppSecurity {

    fun securityWebFilterChain(
        httpSecurity: HttpSecurity
    ):SecurityFilterChain{
        return httpSecurity.csrf{it.disable()}
//            .addFilter()
            .authorizeHttpRequests{
                it.anyRequest().permitAll()
            }
            .build()
    }
}