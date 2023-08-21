package com.example2.employee;

public record Employee(String name, Department department, int yearsWorking, double salary) {

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department=" + department +
                ", yearsWorking=" + yearsWorking +
                ", salary=" + salary +
                '}';
    }
}
