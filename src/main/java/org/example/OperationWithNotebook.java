package org.example;

import java.util.*;
//import java.util.HashSet;
//import java.util.Scanner;

public class OperationWithNotebook {

    private Set<Notebook> notebooks = new HashSet<>();
    private List<Criterion> criterionList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void printList() {
        for (Notebook notebook : notebooks) {
            if (notebookIsCorrect(notebook)) {
                System.out.println(notebook);
            }
        }
    }

    public boolean notebookIsCorrect(Notebook notebook) {

        for (Criterion criterion : criterionList) {
            Object valueNotebook = null;

            switch (criterion.property) {
                case "name" -> valueNotebook = notebook.getName();
                case "aRAM" -> valueNotebook = notebook.getAmRAM();
                case "model" -> valueNotebook = notebook.getModel();
                default -> {
                    continue;
                }
            }

            if (criterion.value != null && !criterion.value.equals(valueNotebook)) {
                return false;
            }
        }
        return true;
    }

    public OperationWithNotebook(Set<Notebook> notebooks) {
        scanner = new Scanner(System.in);
        this.notebooks = notebooks;
        this.criterionList = criterionList;
    }

    public int getCriteria() {
        StringBuilder text = new StringBuilder("Введите цифру: ");

        List<String> properties = propertiesForFilter();

        for (int i = 0; i < properties.size(); i++) {
            text.append("\n").append(i + 1).append(". ").append(getPropertyDescription(properties.get(i)));
        }

        System.out.println(text);

        return scanner.nextInt();
    }

    public String getPropertyDescription(String property) {
        Map<String, String> map = new HashMap<>();

        map.put("name", "Наименование");
        map.put("aRAM", "Обьем оперативной памяти");
        map.put("model", "модель");

        return map.toString();

    }

    public List<String> propertiesForFilter() {
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("aRAM");
        list.add("model");

        return list;
    }

    public String getOperations() {

        String text = """
                Выберите операцию:\s
                 1. Добавить критерий\s
                 2. Вывести список\s
                 3. Завершить""";
        System.out.println(text);

        return scanner.next();
    }

    public Set<String> quantitativeSelection() {
        Set<String> set = new HashSet<>();
        set.add("aRAM");

        return set;
    }

    public void start() {

        boolean flag = true;
        while (flag) {

            String operation = getOperations();
            switch (operation) {
                case "3" -> {
                    flag = false;
                    scanner.close();
                    continue;
                }
                case "1" -> {
                    int criterion = getCriteria();
                    List<String> properties = propertiesForFilter();
                    if (criterion - 1 < 0 || criterion - 1 > properties.size() - 1) {
                        System.out.println("Введено некорректное значение");
                        continue;
                    }
                    String property = properties.get(criterion - 1);
                    Criterion criterionObject = null;
                    try {
                        if (quantitativeSelection().contains(property)) {
                            criterionObject = Criterion.startGetting(scanner, property, true);
                        } else {
                            criterionObject = Criterion.startGetting(scanner, property, false);
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка при выборе критерия");
                        continue;
                    }
                    if (criterionObject != null) {
                        System.out.println("Критерий добавлен");
                        criterionList.add(criterionObject);
                    }
                }
                case "2" -> printList();
            }
        }
    }
}


class Criterion {

    Object value;
    Double minValue;
    Double maxValue;
    boolean isQuantitative;
    String property;

    public Criterion(String property, boolean isQuantitative, Object value, Double minValue, Double maxValue) {
        this.property = property;
        this.isQuantitative = isQuantitative;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static Criterion startGetting(Scanner scanner, String property, boolean isQuantitative) {

        if (isQuantitative) {

            String quest = """
                    Выберите тип криетрия:\s
                     1. Значение
                     2. Меньше
                     3. Больше
                     4. Интервал""";
            System.out.println(quest);

            String text = scanner.next();

            Criterion criterion = null;

            switch (text) {
                case "1" -> {
                    System.out.println("Введите значение поиска: ");
                    int getValue = scanner.nextInt();
                    criterion = new Criterion(property, true, getValue, null, null);
                }
                case "2" -> {
                    System.out.println("Введите максимальное предельное значение: ");
                    double getValue = scanner.nextDouble();
                    criterion = new Criterion(property, true, null, null, getValue);
                }
                case "3" -> {
                    System.out.println("Введите минимальное предельное значение: ");
                    double getValue = scanner.nextDouble();
                    criterion = new Criterion(property, true, null, getValue, null);
                }
                case "4" -> {
                    System.out.println("Введите минимальное предельное значение: ");
                    double getMin = scanner.nextDouble();
                    System.out.println("Введите максимальное предельное значение: ");
                    double getMax = scanner.nextDouble();
                    criterion = new Criterion(property, true, null, getMin, getMax);
                }
            }

            return criterion;
        }

        System.out.println("Введите значение поиска: ");
        String getValue = scanner.next();
        return new Criterion(property, false, getValue, null, null);
    }

}