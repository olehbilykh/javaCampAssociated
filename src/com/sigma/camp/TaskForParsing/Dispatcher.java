package com.sigma.camp.TaskForParsing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Програмне завдання. За допомогою класів Pattern, Matcher / Scanner (за вибором
 * слухача) в заданому текстовому файлі видалити всі слова заданої довжини, що
 * починаються на приголосну букву. Опрацьовані фрагменти тексту заносити до нового
 * текстового файлу безпосередньо після опрацювання без утворення буферних
 * колекцій або без перетворення всього файлу у текст.
 */

public class Dispatcher {
    public static void main(String[] args) {
        Pattern p;
        Matcher m;
        String[] strings;
        String fileContent;

        try {
            fileContent = Files.readString(Path.of("src/com/sigma/camp/TaskForParsing/input"));
            System.out.println(fileContent);
//            p = Pattern.compile("\\w+");
            p = Pattern.compile("[^aeiou].+?[.,!? ]");

            m = p.matcher(fileContent);
            while (m.find()){
                System.out.println(m.group());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
