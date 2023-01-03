package com.sigma.camp.TaskForFiles;


import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Dispatcher {
    public static void main(String[] args) throws IOException {
        final File filesInFolder = new File("src/com/sigma/camp/TaskForFiles/InputFiles");
        List<File> files = listFilesForFolder(filesInFolder);
        String tmp;
        String[] strings;
        PrintWriter pw;

        List<Drink> lessThan05 = new LinkedList<>();
        List<Drink> moreThan05lessThan1 = new LinkedList<>();
        List<Drink> moreThan1 = new LinkedList<>();


        for (int i = 0; i < files.size(); i++) {
            try (BufferedReader bf = new BufferedReader(new FileReader(listFilesForFolder(filesInFolder).get(i)))) {

                while ((tmp = bf.readLine()) != null) {
                    strings = tmp.split(" ");
                    Drink drink = new Drink(Integer.parseInt(strings[0]), strings[1], Double.parseDouble(strings[2]), strings[3]);


                    if (drink.getVolume() < 0.51) {
                        lessThan05.add(drink);
                    } else if (drink.getVolume() > 0.5 && drink.getVolume() < 1) {
                        moreThan05lessThan1.add(drink);
                    } else {
                        moreThan1.add(drink);
                    }

                }
            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        }
        Dispatcher.sort(lessThan05, args[0]);
        pw = new PrintWriter(new FileWriter("src/com/sigma/camp/TaskForFiles/OutputFiles/lessThan05"));
        for (Drink drink : lessThan05) {
            pw.println(drink);
        }
        pw.flush();
        pw.close();

        Dispatcher.sort(moreThan05lessThan1, args[1]);
        pw = new PrintWriter(new FileWriter("src/com/sigma/camp/TaskForFiles/OutputFiles/moreThan05lessThan1"));
        for (Drink drink : moreThan05lessThan1) {
            pw.println(drink);
        }
        pw.flush();
        pw.close();


        Dispatcher.sort(moreThan1, args[2]);
        pw = new PrintWriter(new FileWriter("src/com/sigma/camp/TaskForFiles/OutputFiles/moreThan1"));
        for (Drink drink : moreThan1) {
            pw.println(drink);
        }
        pw.flush();
        pw.close();


        System.out.println("< 0.5 " + lessThan05);
        System.out.println("> 0.5 && 1 < " + moreThan05lessThan1);
        System.out.println("> 1 " + moreThan1);

    }

    public static void sort(List<Drink> drinks, String input) {
        switch (input) {
            case "1" -> drinks.sort((d1, d2) -> {
                int result = d1.getType().compareTo(d2.getType());
                if (result == 0) {
                    result = Double.compare(d1.getVolume(), d2.getVolume());
                    if (result == 0) {
                        result = d1.getBottleMaterial().compareTo(d2.getBottleMaterial());
                    }
                }
                return result;
            });

            case "2" -> drinks.sort((d1, d2) -> {
                int result = d1.getBottleMaterial().compareTo(d2.getBottleMaterial());
                if (result == 0) {
                    result = Double.compare(d1.getVolume(), d2.getVolume());
                    if (result == 0) {
                        result = d1.getType().compareTo(d2.getType());
                    }
                }
                return result;
            });

            default -> drinks.sort(Drink::compareTo);
        }
    }

    public static List<File> listFilesForFolder(final File folder) {
        List<File> fileEntries = new LinkedList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                fileEntries.add(fileEntry);
            }
        }
        return fileEntries;
    }
}

class Drink implements Comparable<Drink> {
    private int id;
    private String type;
    private double volume;
    private String bottleMaterial;

    public Drink(int id, String type, double volume, String bottleMaterial) {
        this.id = id;
        this.type = type;
        this.volume = volume;
        this.bottleMaterial = bottleMaterial;
    }

    @Override
    public int compareTo(Drink o) {
        int result = Double.compare(this.getVolume(), o.getVolume());
        if (result == 0) {
            result = this.bottleMaterial.compareTo(o.bottleMaterial);
            if (result == 0) {
                result = this.type.compareTo(o.type);
            }
        }

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getBottleMaterial() {
        return bottleMaterial;
    }

    public void setBottleMaterial(String bottleMaterial) {
        this.bottleMaterial = bottleMaterial;
    }

    @Override
    public String toString() {
        return id + " " + type + " " + volume + " " + bottleMaterial;
    }
}