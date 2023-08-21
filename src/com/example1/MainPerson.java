package com.example1;

import com.example1.Person.Gender;
import com.example1.Person.Person;
import com.util.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPerson {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("""
                                        
                    Select an option:
                    0- Exit
                    1- Filter females with imperative approach
                    2- Filter females with declarative approach
                    3- Sort by age ascending
                    4- Sort by age descending
                    5- AllMatch by age greater than XX (return true/false)
                    6- AnyMatch by age greater than XX (return true/false)
                    7- NoneMatch by name [name] (return true/false)
                    8- Max age
                    9- Min age
                    10- Group by gender
                    11- Name of the oldest female
                    12- The youngest male
                    """);
            option = sc.nextInt();

            switch (option) {
                case 0 -> {
                }
                case 1 -> filterFemalesImperative();
                case 2 -> filterFemalesDeclarative();
                case 3 -> sortByAgeAcending();
                case 4 -> sortByAgeDescending();
                case 5 -> allMatchByAge(Utils.askAge());
                case 6 -> anyMatchByAge(Utils.askAge());
                case 7 -> noneMatchByName(Utils.askName());
                case 8 -> maxAge();
                case 9 -> minAge();
                case 10 -> groupByGender();
                case 11 -> oldestFemale();
                case 12 -> youngestMale();
                default -> System.out.println("Incorrect option");

            }
        } while (option != 0);
    }

    private static void youngestMale() {
        getPeople().stream()
                .filter(person -> person.gender().equals(Gender.MALE))
                .min(Comparator.comparing(Person::age))
                .ifPresent(System.out::println);
    }

    private static void oldestFemale() {
        getPeople().stream()
                .filter(person -> person.gender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::age))
                .map(Person::name)
                .ifPresent(System.out::println);
    }

    private static void groupByGender() {
        Map<Gender, List<Person>> groupByGender = getPeople().stream()
                .collect(Collectors.groupingBy(Person::gender));

        groupByGender.forEach((gender, person) -> {
            System.out.println(gender);
            person.forEach(System.out::println);
        });
    }

    private static void minAge() {
        getPeople().stream()
                .min(Comparator.comparing(Person::age))
                .ifPresent(System.out::println);
    }

    private static void maxAge() {
        getPeople().stream()
                .max(Comparator.comparing(Person::age))
                .ifPresent(System.out::println);
    }

    private static void noneMatchByName(String name) {
        System.out.println(
                getPeople().stream()
                        .noneMatch(person -> person.name().equalsIgnoreCase(name))
        );
    }

    private static void anyMatchByAge(int age) {
        System.out.println(
                getPeople().stream()
                        .anyMatch(person -> person.age() > age)
        );
    }

    private static void allMatchByAge(int age) {
        System.out.println(
                getPeople().stream()
                        .allMatch(person -> person.age() > age)
        );
    }

    private static void sortByAgeDescending() {
        getPeople().stream()
                .sorted(Comparator.comparing(Person::age)
                        .reversed()
                        .thenComparing(Person::gender)) //follow the order in Gender enum class
                .forEach(System.out::println);
    }

    private static void sortByAgeAcending() {
        getPeople().stream()
                .sorted(Comparator.comparing(Person::age)
                        .thenComparing(Person::gender)) //follow the order in Gender enum class
                .forEach(System.out::println);
    }

    private static void filterFemalesDeclarative() {
        getPeople().stream()
                .filter(person -> person.gender().equals(Gender.FEMALE))
                .forEach(System.out::println);
    }

    private static void filterFemalesImperative() {
        List<Person> females = new ArrayList<>();
        for (Person person : getPeople()) {
            if (person.gender().equals(Gender.FEMALE))
                females.add(person);
        }
        females.forEach(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("James Bond", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 20, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
