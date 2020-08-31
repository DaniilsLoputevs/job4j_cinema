package ru.job4j.cinema.ahelptools;

public class ConslLog {

    public static void log(String desc, Object object) {
        System.out.println("ConslLog: " + desc + ": " + object);
    }
    public static void log(String desc) {
        System.out.println("ConslLog: " + desc);
    }

}
