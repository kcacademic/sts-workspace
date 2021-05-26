package com.kchandrakant.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.kchandrakant.learning.domain.Employee;

import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@EnableTransactionManagement
public class Service {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee insertRecord(Employee employee) {
        entityManager.persist(employee);
        jmsTemplate.convertAndSend("TEST.FOO", employee.toString());
        //throw new RuntimeException("Simulating a Failure");
        return employee;
    }

    public List<Employee> getAll() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        List<Employee> employees = transactionTemplate.execute(status -> {
            return entityManager.createQuery("Select e from Employee e", Employee.class).getResultList();
        });

        return employees;
    }

    public void listen() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.executeWithoutResult(status -> {
            jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
            String emp = (String) jmsTemplate.receiveAndConvert("TEST.FOO");
            System.out.println("Message Received: " + emp);
        });
    }
}
