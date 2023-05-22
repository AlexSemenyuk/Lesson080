package org.itstep.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        String publishedDB = "16.05.2023 11:22:38";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime published = LocalDateTime.parse(publishedDB, formatter);
        System.out.println("published = " + published);
    }
}
