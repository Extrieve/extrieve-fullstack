package com.extrieve.employeemanagement.controller;

import com.extrieve.employeemanagement.model.Employee;
import com.extrieve.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/save")
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
