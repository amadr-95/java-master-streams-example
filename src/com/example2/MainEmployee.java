package com.example2;

import com.example2.employee.Department;
import com.example2.employee.Employee;
import com.util.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("""
                                        
                    Pick an option:
                    0- Exit
                    1- Employees whose salary is greater than XX
                    2- Employees whose salary is greater than XX and their names contains the letter X
                    3- Employees with salary greater than XX or years of working less than X
                    4- Department names
                    5- X employees who have more worked years
                    6- Find the first name [name]
                    7- Sum of salary from all employees
                    8- Minimum salary
                    9- Maximum salary
                    """);
            option = sc.nextInt();
            switch (option) {
                case 0 -> {
                }
                case 1 -> filterBySalaryGreaterThan(Utils.askSalary());
                case 2 -> filterBySalaryGreaterThanAndContainsLetter(Utils.askSalary(),
                        Utils.askLetter());
                case 3 -> filterBySalaryGreaterThanOrYearsWorkingLessThan(Utils.askSalary(),
                        Utils.askYearsWorking());
                case 4 -> distinctDepartmentNames();
                case 5 -> employeesWithMoreWorkedYears(Utils.askLimit());
                case 6 -> findFirstName(Utils.askName());
                case 7 -> sumOfAllSalaries();
                case 8 -> minSalary();
                case 9 -> maxSalary();
            }
        } while (option != 0);
    }

    private static void maxSalary() {
        getPeople().stream()
                .max(Comparator.comparing(Employee::salary))
                .map(Employee::salary)
                .ifPresent(System.out::println);
    }

    private static void minSalary() {
        getPeople().stream()
                .min(Comparator.comparing(Employee::salary))
                .map(Employee::salary)
                .ifPresent(System.out::println);
    }

    private static void sumOfAllSalaries() {
        Double sum = getPeople().stream()
                .map(Employee::salary)
                .reduce(0.0, Double::sum);
        System.out.println(sum);
    }

    public static void findFirstName(String name) {
        getPeople().stream()
                .filter(employee -> employee.name().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static void employeesWithMoreWorkedYears(int limit) {
        getPeople().stream()
                .sorted(Comparator.comparing(Employee::yearsWorking).reversed())
                .limit(limit)
                //.map(Employee::name)
                .forEach(System.out::println);
        /*
        getPeople().stream()
             .sorted( (e1, e2) -> -Integer.compare(e1.yearsWorking(), e2.yearsWorking()))
             .limit(3)
             .forEach(System.out::println);
         */
    }

    private static void distinctDepartmentNames() {
        getPeople().stream()
                .map(Employee::department)
                .distinct()
                .map(Department::toString)
                .forEach(departament -> System.out.println(departament.toLowerCase()));
    }

    private static void filterBySalaryGreaterThanOrYearsWorkingLessThan(double salary,
                                                                        int years) {
        getPeople().stream()
                .filter(employee ->
                        employee.salary() > salary || employee.yearsWorking() < years)
                //.map(Employee::name)
                .sorted(Comparator.comparing(Employee::name))
                .forEach(System.out::println);
    }

    private static void filterBySalaryGreaterThanAndContainsLetter(Double salary,
                                                                   String letter) {
        getPeople().stream()
                .filter(employee -> employee.salary() > salary)
                .map(Employee::name)
                .map(String::toLowerCase)
                .filter(name -> name.contains(letter))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static void filterBySalaryGreaterThan(double salary) {
        getPeople().stream()
                .filter(employee -> employee.salary() > salary)
                //.map(Employee::name) //if we want the name instead the whole object
                .forEach(System.out::println);
    }

    private static List<Employee> getPeople() {
        return List.of(
                new Employee("Pedro", Department.ACCOUNTING, 5, 2000),
                new Employee("Juan", Department.HUMAN_RESOURCES, 1, 2000),
                new Employee("María", Department.MANAGEMENT, 6, 3000),
                new Employee("Anabel", Department.ENGINEERING, 2, 1500),
                new Employee("Tomás", Department.ACCOUNTING, 4, 1600),
                new Employee("Samuel", Department.MANAGEMENT, 1, 2100),
                new Employee("Irene", Department.HUMAN_RESOURCES, 3, 1700),
                new Employee("Laura", Department.ENGINEERING, 8, 2000),
                new Employee("Gonzalo", Department.ENGINEERING, 8, 2000)
        );
    }
}
