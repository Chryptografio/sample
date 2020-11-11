package com.endava.exercise;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
public class AppConfig {
    @Bean
    public BeanNameGenerator beanNameGenerator() {
        return new NameChanger();
    }
}
