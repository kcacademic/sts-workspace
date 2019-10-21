package com.baeldung.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyBean implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, BeanPostProcessor, InitializingBean, DisposableBean {

    public MyBean() {
        System.out.println("Constructor of MyBean called !! ");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory method of MyBean is called");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName method of MyBean is called");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext method of MyBean is called");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Post Process Before Initialization method is called : Bean Name " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Post Process After Initialization method is called : Bean Name " + beanName);
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy method of MyBean bean called !! ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet method of MyBean bean is called !! ");
    }

}
