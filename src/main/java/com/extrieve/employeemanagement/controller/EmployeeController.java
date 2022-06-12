package com.extrieve.employeemanagement.controller;

import com.extrieve.employeemanagement.model.Employee;
import com.extrieve.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
