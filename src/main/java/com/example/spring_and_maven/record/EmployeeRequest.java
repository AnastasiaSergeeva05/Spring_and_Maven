package com.example.spring_and_maven.record;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private  int department;
    private int salary;

    public EmployeeRequest(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}
