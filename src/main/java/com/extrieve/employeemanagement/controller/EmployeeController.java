package com.extrieve.employeemanagement.controller;

import com.extrieve.employeemanagement.exception.ResourceNotFoundException;
import com.extrieve.employeemanagement.model.Employee;
import com.extrieve.employeemanagement.repository.EmployeeRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Logger logger;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        logger.info("Fetching all employees");
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("Fetching employee by id: " + id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok().body(employee);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/{firstName}")
    public Collection<Employee> getEmployeeByFirstName(@PathVariable String firstName) {
        logger.info("Fetching employees by first name: " + firstName);
        Collection<Employee> employeeList = employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
        if (employeeList.isEmpty()) {
            throw new ResourceNotFoundException("Employee not found");
        }
        return employeeList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        logger.info("Saving new employee");
        return employeeRepository.save(employee);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Updating employee of id: " + id);
        Employee employee1 = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee1);
        return ResponseEntity.ok().body(updatedEmployee);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        logger.info("Deleting employee with id: " + id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().body("Employee deleted successfully");
    }
}
