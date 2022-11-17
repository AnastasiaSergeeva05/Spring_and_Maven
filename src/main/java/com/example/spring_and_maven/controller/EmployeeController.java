package com.example.spring_and_maven.controller;

import com.example.spring_and_maven.exception.InvalidEmployeeRequstException;
import com.example.spring_and_maven.model.Employee;
import com.example.spring_and_maven.record.EmployeeRequest;
import com.example.spring_and_maven.service.EmployeeService;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();

    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployees(@RequestBody EmployeeRequest employeeRequest) {
        try {
            return ResponseEntity.ok( this.employeeService.addEmployees(employeeRequest));
        } catch (InvalidEmployeeRequstException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("employees/min/salary")
    public Employee getEmployeeWithMinSalary() {
        return this.employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("employees/max/salary")
    public Employee getEmployeeWithMaxSalary() {
        return this.employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("employees/highSalary")
    public Double  getEmployeeWithSalaryMoreThatAverage(){
        return this.employeeService.getAverageSalary();
    }
}
