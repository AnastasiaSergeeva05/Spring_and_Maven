package com.example.spring_and_maven.service;

import com.example.spring_and_maven.exception.EmployeeNotFoundException;
import com.example.spring_and_maven.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartamentService {

    private final EmployeeService employeeService;

    public DepartamentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Collection<Employee> getDepartamentEmployee(int departament) {
        return getEmployeeByDepartamentStream(departament)
                .collect(Collectors.toList());
    }


    public int getSumOfSalariesByDeportament(int departament) {
        return getEmployeeByDepartamentStream(departament)
                .mapToInt(Employee::getSalary).sum();
    }


    public int getSumMaxSalaryByDepartament(int departament) {
        return getEmployeeByDepartamentStream(departament)
                .mapToInt(Employee::getSalary)
                .max().
                orElseThrow(EmployeeNotFoundException::new);
    }


    public int getSumMinSalaryByDepartament(int departament) {
        return getEmployeeByDepartamentStream(departament)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Map<Integer, List<Employee>> getEmployeeGroupedByDepartaments() {
        return employeeService
                .getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }

    private Stream<Employee> getEmployeeByDepartamentStream(int departament) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> e.getDepartament() == departament);
    }
}

