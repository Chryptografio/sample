package com.endava.exercise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NameChangerBPP implements BeanPostProcessor {
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final Bean annotation = bean.getClass().getAnnotation(Bean.class);
        final Annotation[] annotations = bean.getClass().getAnnotations();
        System.out.println(beanName + ":");
        for (Annotation annotation1 : annotations) {
            System.out.println(annotation1.toString());
        }
        System.out.println("---------");
        if (annotation != null) {
            final Field[] declaredFields = annotation.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField.getName());
            }
        }

//        try {
//            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
//            final Object bean1 = beanFactory.getBean(beanName);
//            final String factoryBeanName = beanDefinition.getFactoryBeanName();
//            System.out.println(beanName + " is factory bean: " + (bean1 instanceof FactoryBean));
//            System.out.println(beanName +  ": " + factoryBeanName);
//            beanDefinition.setFactoryBeanName(beanDefinition.getFactoryBeanName() + " ehehhehe");
//            final String factoryBeanNameOther = beanDefinition.getFactoryBeanName();
//            System.out.println(beanName +  ": " + factoryBeanNameOther);
//            System.out.println("-------------");
//        } catch (Exception e) {
//            System.out.println("ERR: Bean with name " + beanName + " not found");
//            System.out.println("-------------");
//        }

//        final BeanFactory parentBeanFactory = appContext.getParentBeanFactory();
//        appContext.
//        beanDefinition.setFactoryBeanName(beanDefinition.getFactoryBeanName() + "  ehehhe");
//        beanDefinition.setBeanClassName(beanDefinition.getBeanClassName() + "  ehehhehe");
        return bean;
    }
}
