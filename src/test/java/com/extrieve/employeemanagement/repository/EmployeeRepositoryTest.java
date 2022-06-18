package com.extrieve.employeemanagement.repository;

import com.extrieve.employeemanagement.exception.ResourceNotFoundException;
import com.extrieve.employeemanagement.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findAllEmployees() {
        ResponseEntity<List<Employee>> response = ResponseEntity.ok(employeeRepository.findAll());
        Objects.requireNonNull(response.getBody()).forEach(System.out::println);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findEmployeeById() {
        Employee employee = employeeRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        assertEquals("Heindrick", employee.getFirstName());
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