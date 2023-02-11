package org.example;

import java.util.ArrayList;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Notebook {
    private String name;
    private int aRAM;
    private String model;

    public Notebook(String name, int aRAM, String model) {
        this.name = name;
        this.aRAM = aRAM;
        this.model = model;
    }

    public boolean validateObject() {
        return true;
    }

    public static List<String> propertiesForFilter() {
        List<String> list = new ArrayList<>();
        list.add("aRAM");
        list.add("model");

        return list;
    }

    @Override
    public String toString() {
        return "Ноутбук: (" + name + "): " + "количество памяти:" + aRAM + model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmRAM() {
        return aRAM;
    }

    public void setAmRAM(int aRAM) {
        this.aRAM = aRAM;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}