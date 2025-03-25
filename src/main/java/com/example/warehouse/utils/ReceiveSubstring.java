package com.example.warehouse.utils;

public class ReceiveSubstring {
    public static String extractSubstringAfterUnderscore(String input) {
        int underscoreIndex = input.indexOf("_");
        if (underscoreIndex != -1) {
            return input.substring(underscoreIndex + 1);
        }
        return "";
    }

    public static String extractSubstringBeforeUnderscore(String input) {
        int underscoreIndex = input.indexOf("_");
        if (underscoreIndex != -1) {
            return input.substring(0, underscoreIndex);
        }
        return "";
    }




}
