package com.endava.exercise;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

@Component
public class AppStarter implements CommandLineRunner {
    private final ApplicationContext applicationContext;
    private final BeanFactory beanFactory;

    @Autowired
    public AppStarter(ApplicationContext applicationContext, BeanFactory beanFactory) {
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        final String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        for (String beanDefinitionName : beanDefinitionNames) {
            applicationContext.getBean(beanDefinitionName);
        }

        System.out.println("application context:");
        System.out.println(applicationContext.getClass().getName());
        final Field[] declaredFields = applicationContext.getClass().getDeclaredFields();
        System.out.println("Declared fields:");
        for (Field declaredField : declaredFields) {
            System.out.println("field name: " + declaredField.getName());
            System.out.println("field class: " + declaredField.getClass().getName() + "\n");
        }
        System.out.println("Declared methods:");
        final Method[] declaredMethods = applicationContext.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("method name: " + declaredMethod.getName());
            System.out.println("return type: " + declaredMethod.getAnnotatedReturnType() + "\n");
        }

        System.out.println("bean factory: ");
        System.out.println(beanFactory.getClass().getName());
        final Field[] declaredFieldsBF = beanFactory.getClass().getDeclaredFields();
        for (Field field : declaredFieldsBF) {
            System.out.println("field name: " + field.getName());
            System.out.println("field class: " + field.getClass().getName() + "\n");
        }
        final Method[] declaredMethodsBF = beanFactory.getClass().getDeclaredMethods();
        for (Method method : declaredMethodsBF) {
            System.out.println("method name: " + method.getName());
            System.out.println("return type: " + method.getAnnotatedReturnType() + "\n");

        }

        System.out.println("----------------");

        final Method beanNamesIterator = beanFactory.getClass().getDeclaredMethod("getBeanNamesIterator");
        System.out.println("Got it!");
        @SuppressWarnings("unchecked") final Iterator<String> iterator = (Iterator<String>) beanNamesIterator.invoke(beanFactory);
        System.out.println("Bean names: ");
        while (iterator.hasNext()) {
            final String next = iterator.next();
            System.out.println(next);
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


        final Method beanNamesIterator1 = beanFactory.getClass().getDeclaredMethod("getBeanNamesIterator");
        System.out.println("-----------------------------");
        @SuppressWarnings("unchecked") final Iterator<String> iterator1 = (Iterator<String>) beanNamesIterator1.invoke(beanFactory);
        System.out.println("Changed bean names: ");
        while (iterator1.hasNext()) {
            final String next = iterator1.next();
            System.out.println(next);
        }
        //final Object somebean = beanFactory.getBean("org.springframework.context.annotation.internalConfigurationAnnotationProcessorhello");
    }
}
