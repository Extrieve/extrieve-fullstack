package com.extrieve.employeemanagement.repository;

import com.extrieve.employeemanagement.exception.ResourceNotFoundException;
import com.extrieve.employeemanagement.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findAllEmployees() {
        Collection<Employee> employeeList = employeeRepository.findAll();
//        Object employee;
        for (Employee employee : employeeList) {
            System.out.println(employee.getId());
            System.out.println(employee.getFirstName() + '\n');
        }
//        assertEquals(3, employeeList.size());
    }

    @Test
    void findEmployeeById() {
        Employee employee = employeeRepository.findById(100L).get();
        System.out.println(employee.getId());
        System.out.println(employee.getFirstName() + '\n');
    }

    @Test
    void findByFirstNameContaining() {
        Collection<Employee> employeeList = employeeRepository.findByFirstNameContainingIgnoreCase("ab");
        System.out.println(employeeList);
//        assertEquals(1, employeeList.size());
    }

    @Test
    void findByFirstName() {
        Collection<Employee> employeeList = employeeRepository.findByFirstName("Jonell");
        System.out.println(employeeList);
    }
}