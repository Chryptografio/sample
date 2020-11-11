package com.endava.exercise;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
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
        final Method beanNamesIterator = beanFactory.getClass().getDeclaredMethod("getBeanNamesIterator");
        @SuppressWarnings("unchecked")
        final Iterator<String> iterator1 = (Iterator<String>) beanNamesIterator.invoke(beanFactory);
        System.out.println("Bean names: ");
        while (iterator1.hasNext()) {
            final String next = iterator1.next();
            System.out.println(next);
        }
    }
}
