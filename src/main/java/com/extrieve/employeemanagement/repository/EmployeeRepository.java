package com.extrieve.employeemanagement.repository;

import com.extrieve.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Collection<Employee> findByFirstNameContaing(String firstName);
}
