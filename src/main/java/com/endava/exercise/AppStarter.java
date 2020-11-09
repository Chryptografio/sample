package com.endava.exercise;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Iterator;

@Component
public class AppStarter implements CommandLineRunner {
    private final BeanFactory beanFactory;

    @Autowired
    public AppStarter(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("bean factory: ");
        System.out.println(beanFactory.getClass().getName());

        final Method beanNamesIterator = beanFactory.getClass().getDeclaredMethod("getBeanNamesIterator");
        @SuppressWarnings("unchecked") final Iterator<String> iterator = (Iterator<String>) beanNamesIterator.invoke(beanFactory);
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (next.equals("autoConfigurationReport")
                    || next.equals("org.springframework.boot.context.ContextIdApplicationContextInitializer$ContextId")
                    || next.equals("springApplicationArguments")
                    || next.equals("springBootBanner")
                    || next.equals("springBootLoggingSystem")
                    || next.equals("springBootLoggerGroups")
                    || next.equals("environment")
                    || next.equals("systemProperties")
                    || next.equals("systemEnvironment")
                    || next.equals("org.springframework.context.annotation.ConfigurationClassPostProcessor.importRegistry")
                    || next.equals("messageSource")
                    || next.equals("applicationEventMulticaster")) {
                continue;
            }
            final Method getBeanDefinition = beanFactory.getClass().getDeclaredMethod("getBeanDefinition", String.class);
            final BeanDefinition beanDefinition = (BeanDefinition) getBeanDefinition.invoke(beanFactory, next);
            final Method removeBeanDefinition = beanFactory.getClass().getDeclaredMethod("removeBeanDefinition", String.class);
            final Method registerBeanDefinition = beanFactory.getClass().getDeclaredMethod("registerBeanDefinition", String.class, BeanDefinition.class);
            removeBeanDefinition.invoke(beanFactory, next);
            registerBeanDefinition.invoke(beanFactory, next + "hello", beanDefinition);
        }

        @SuppressWarnings("unchecked") final Iterator<String> iterator1 = (Iterator<String>) beanNamesIterator.invoke(beanFactory);
        System.out.println("Changed bean names: ");
        while (iterator1.hasNext()) {
            final String next = iterator1.next();
            System.out.println(next);
        }
    }
}
