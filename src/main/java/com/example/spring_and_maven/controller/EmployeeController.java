package com.example.spring_and_maven.controller;

import com.example.spring_and_maven.model.Employee;
import com.example.spring_and_maven.record.EmployeeRequest;
import com.example.spring_and_maven.service.EmployeeService;

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
    public Employee createEmployees(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.getEmployees(employeeRequest);
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
    public List<Employee>getEmployeeWithSalaryMoreThatAverage(){
        return this.employeeService.getEmployeeWithSalaryMoreThatAverage();
    }
}
