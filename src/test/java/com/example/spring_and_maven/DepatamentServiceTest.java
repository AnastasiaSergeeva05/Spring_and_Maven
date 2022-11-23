package com.example.spring_and_maven;

import com.example.spring_and_maven.exception.EmployeeNotFoundException;
import com.example.spring_and_maven.model.Employee;
import com.example.spring_and_maven.record.EmployeeRequest;
import com.example.spring_and_maven.service.DepartamentService;
import com.example.spring_and_maven.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DepatamentServiceTest {
    private final List<Employee> employees = (List.of(
            new Employee("Test", "Test", 1, 5000),
            new Employee("Testq", "Testa", 1, 50000),
            new Employee("Testw", "Tests", 1, 30000),
            new Employee("Teste", "Testd", 1, 10000),
            new Employee("Testr", "Testf", 1, 25000),
            new Employee("Testt", "Testg", 2, 60000),
            new Employee("Testy", "Testh", 2, 70000)));

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartamentService departamentService;

    @BeforeEach
    void setUp() {
        when(employeeService.getAllEmployees()).thenReturn(employees);

    }

    @Test
    void getEmployeesByDepartament() {
        Collection<Employee> employeesList = this.departamentService.getDepartamentEmployee(1);
        assertThat(employeesList).hasSize(5).contains(employees.get(0), employees.get(1), employees.get(2), employees.get(3), employees.get(4));

    }

    @Test
    void sumOfSalariesDepartament() {
        int sum = this.departamentService.getSumOfSalariesByDeportament(1);
        assertThat(sum).isEqualTo(120_000);
    }

    @Test
    void sumOfSalariesDepartament2() {
        int sum = this.departamentService.getSumOfSalariesByDeportament(2);
        assertThat(sum).isEqualTo(130_000);
    }

    @Test
    void maxSalaryDepartament() {
        int max = this.departamentService.getSumMaxSalaryByDepartament(1);
        assertThat(max).isEqualTo(50_000);
    }

    @Test
    void maxSalaryDepartament2() {
        int max = this.departamentService.getSumMaxSalaryByDepartament(2);
        assertThat(max).isEqualTo(70_000);
    }

    @Test
    void minSalaryDepatament() {
        int min = this.departamentService.getSumMinSalaryByDepartament(1);
        assertThat(min).isEqualTo(5000);
    }

    @Test
    void minSalaryDepatament2() {
        int min = this.departamentService.getSumMinSalaryByDepartament(2);
        assertThat(min).isEqualTo(60_000);
    }


    @Test
    void groopedEmployees() {

        Map<Integer, List<Employee>> groopedEmployees = this.departamentService.getEmployeeGroupedByDepartaments();
        assertThat(groopedEmployees)
                .hasSize(2)
                .containsEntry(1, List.of(employees.get(0), employees.get(1), employees.get(2), employees.get(3), employees.get(4)))
                .containsEntry(2, List.of(employees.get(5), employees.get(6)));

    }

    @Test
    void WhenNoEmployeesThenGroupByReturnEmptyMap(){
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        Map<Integer, List<Employee>>gropedEmployees = this.departamentService.getEmployeeGroupedByDepartaments();
        assertThat(gropedEmployees).isEmpty();
    }


    @Test
    void WhenNoEmployeesThenMaxSalaryInDepartamentThrowsException(){
        when(employeeService.getAllEmployees()).thenReturn(List.of());
        Assertions.assertThatThrownBy(()->departamentService.getSumMinSalaryByDepartament(0)).isInstanceOf(EmployeeNotFoundException.class);

    }
}

