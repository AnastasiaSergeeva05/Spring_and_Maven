package com.example.spring_and_maven.service;

import com.example.spring_and_maven.exception.EmployeeNotFoundException;
import com.example.spring_and_maven.model.Employee;

import com.example.spring_and_maven.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();


    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }


    public Employee getEmployees(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Ошибка: ");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    public Employee getEmployeeWithMinSalary() {
        return employees.values().stream().min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee getEmployeeWithMaxSalary() {
        return employees.values().stream().max(Comparator.comparingInt(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeeWithSalaryMoreThatAverage() {
        Double averageSalary = getAverageSalary();
        if (averageSalary == null) {
            return Collections.emptyList();
        }
        return employees.values().stream().filter(e -> e.getSalary() > averageSalary).collect(Collectors.toList());
    }


    public Double getAverageSalary() {
        return employees.values().stream().collect(Collectors.averagingInt(Employee::getSalary));
    }
}
