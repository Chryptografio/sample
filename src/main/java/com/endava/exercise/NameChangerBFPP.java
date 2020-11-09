package com.endava.exercise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;
import java.util.Iterator;

public class NameChangerBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        final Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) {
            final String next = beanNamesIterator.next();
            try {
                final Object bean = beanFactory.getBean(next);
                System.out.println(next);
            } catch (Exception e) {
                System.out.println("bean with name " + next + " doesn't exist.");
            }
        }
        System.out.println("next part");
        Arrays.stream(beanDefinitionNames).forEach(name -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
//            final Object bean = beanFactory.getBean(name);
            String factoryBeanName = beanDefinition.getFactoryBeanName();
            System.out.println(factoryBeanName);
            if (factoryBeanName != null /*&& bean instanceof FactoryBean*/) {
//                beanDefinition.setFactoryBeanName(beanDefinition.getFactoryBeanName() + " hehhe");
//                System.out.println(beanDefinition.getFactoryBeanName());
            }
            System.out.println("--------");
        });
    }
}
