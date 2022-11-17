package com.example.spring_and_maven.record;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private  int department;
    private int salary;

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
