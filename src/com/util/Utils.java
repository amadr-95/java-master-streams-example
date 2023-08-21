package com.util;

import java.util.Scanner;

public class Utils {
    private static final Scanner scText = new Scanner(System.in);
    private static final Scanner scNum = new Scanner(System.in);

    public static String askName(){
        System.out.println("Name:");
        return scText.nextLine();
    }

    public static double askSalary() {
        System.out.println("Salary:");
        return scNum.nextDouble();
    }

    public static int askYearsWorking() {
        System.out.println("Years working:");
        return scNum.nextInt();
    }

    public static String askLetter() {
        System.out.println("Letter:");
        return scText.nextLine().toLowerCase();
    }

    public static int askLimit() {
        System.out.println("Limit:");
        return scNum.nextInt();
    }

    public static int askAge() {
        System.out.println("Age:");
        return scNum.nextInt();
    }
}
