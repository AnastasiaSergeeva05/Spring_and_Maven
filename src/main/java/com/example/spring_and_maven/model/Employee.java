package com.example.spring_and_maven.model;

public class Employee {
    private static int counter;
    private int id;

    public Employee(String firstName, String lastName, int departament, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departament = departament;
        this.salary = salary;

        this.id = counter++;
    }

    private final String firstName;
    private final String lastName;
    private int departament;
    private final int salary;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartament() {
        return departament;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departament=" + departament +
                ", salary=" + salary +
                '}';
    }
}
