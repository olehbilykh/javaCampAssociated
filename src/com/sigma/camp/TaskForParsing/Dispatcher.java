package com.sigma.camp.TaskForParsing;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
    public static void main(String[] args) throws IOException {
        Pattern p;
        Matcher m;
        String fileContent;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/com/sigma/camp/TaskForParsing/output"))) {
            fileContent = Files.readString(Path.of("src/com/sigma/camp/TaskForParsing/input"));
            p = Pattern.compile("(?i)\\b[a-z&&[^aeiuo]][a-z]*+\\b");
            m = p.matcher(fileContent);

            while (m.find()) {
                bufferedWriter.write(m.group() + " ");
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
