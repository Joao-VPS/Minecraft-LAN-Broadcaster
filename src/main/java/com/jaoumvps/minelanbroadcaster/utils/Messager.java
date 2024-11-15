package com.jaoumvps.minelanbroadcaster.utils;

public class Messager {
    public static void print(String message, Character colorCode) {
        String escape = switch (colorCode.toString().toLowerCase()) {
            case "0" -> "\033[30m";
            case "1" -> "\033[34m";
            case "2" -> "\033[32m";
            case "3" -> "\033[36m";
            case "4" -> "\033[31m";
            case "5" -> "\033[35m";
            case "6" -> "\033[33m";
            case "7" -> "\033[37m";
            case "8" -> "\033[90m";
            case "9" -> "\033[94m";
            case "a" -> "\033[92m";
            case "b" -> "\033[96m";
            case "c" -> "\033[91m";
            case "d" -> "\033[95m";
            case "e" -> "\033[93m";
            case "f" -> "\033[97m";
            default -> "\033[0m";
        };

        System.out.println(escape + message + "\033[0m");
    }
}
