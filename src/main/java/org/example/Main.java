package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Notebook> set = new HashSet<>();
        set.add(new Notebook("Notebook 1", 2, " Acer"));
        set.add(new Notebook("Notebook 1", 4, " Asus"));
        set.add(new Notebook("Notebook 1", 2, " HP"));

        OperationWithNotebook operation = new OperationWithNotebook(set);
        operation.start();
    }
}