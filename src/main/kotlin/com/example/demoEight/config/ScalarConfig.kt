package com.example.demoEight.config


import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class ScalarConfig {

    @Bean
    fun runtimeWiringConfigurer(): RuntimeWiringConfigurer{
        return RuntimeWiringConfigurer { 
            builder: RuntimeWiring.Builder ->
            builder.scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.DateTime)
        }
    }
}