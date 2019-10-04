package com.baeldung.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.baeldung.spring.bean.MyBean;

public class Application {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.baeldung.spring");

        context.getBean(MyBean.class);
        
        context.close();

    }

}
