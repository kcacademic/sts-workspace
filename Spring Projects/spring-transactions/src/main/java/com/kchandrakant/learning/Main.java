package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kchandrakant.learning.domain.Employee;
import com.kchandrakant.learning.service.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

        Service service = context.getBean(Service.class);
        Employee employee = new Employee("Huckleberry Finn",
                new BigDecimal(799.88), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
        System.out.println("Employee Created: " + service.insertRecord(employee));
        List<Employee> employees = service.getAll();
        employees.forEach(e -> System.out.println(e));
        service.listen();

        context.close();
    }

}
