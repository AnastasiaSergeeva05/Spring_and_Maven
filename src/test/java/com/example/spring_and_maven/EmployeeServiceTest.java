package com.example.spring_and_maven;

import com.example.spring_and_maven.model.Employee;
import com.example.spring_and_maven.record.EmployeeRequest;
import com.example.spring_and_maven.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {
    private EmployeeService employeeService;


    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("Test", "Test", 1, 5000),
                new EmployeeRequest("Testq", "Testa", 1, 50000),
                new EmployeeRequest("Testw", "Tests", 1, 30000),
                new EmployeeRequest("Teste", "Testd", 1, 10000),
                new EmployeeRequest("Testr", "Testf", 1, 25000),
                new EmployeeRequest("Testt", "Testg", 2, 60000),
                new EmployeeRequest("Testy", "Testh", 2, 70000)
        ).forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployee() {
        EmployeeRequest request = new EmployeeRequest("Valid", "Valid", 1, 10000);
        Employee result = employeeService.addEmployee(request);

        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), request.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());

        Assertions.assertThat(employeeService.getAllEmployees()).contains(result);

    }

    @Test
    public void listEmployees() {

        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(7);
        Assertions.assertThat(employees)
                .first().
                extracting(Employee::getFirstName).isEqualTo("Test");

    }

    @Test
    public void sumOfSalaries() {
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum).isEqualTo(250_000);
    }

    @Test
    public void employeeWithMaxSalary() {
        Employee employee = employeeService.getEmployeeWithMaxSalary();
        Assertions.assertThat(employee).isNotNull().extracting(Employee::getFirstName).isEqualTo("Testy");
    }

    @Test
    public void employeeWithMinSalary() {
        Employee employee = employeeService.getEmployeeWithMinSalary();
        Assertions.assertThat(employee)
                .isNotNull()
                .extracting(Employee::getFirstName).isEqualTo("Test");
    }

    @Test
    public void removeEmployee() {
        employeeService.removeEmployee(employeeService.getAllEmployees().iterator().next().getId());
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees)
                .hasSize(6);
    }

    @Test

    public void getHighSalaryEmployeesAverage() {
        Collection<Employee> HighSalaryEmployees = employeeService.getEmployeeWithMaxSalaryAverage();
        Assertions.assertThat(HighSalaryEmployees).hasSize(3);
        Assertions.assertThat(HighSalaryEmployees)
                .first()
                .extracting(Employee::getFirstName)
                .isEqualTo("Testq");
    }
}