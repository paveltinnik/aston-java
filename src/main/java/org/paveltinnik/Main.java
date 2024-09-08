package org.paveltinnik;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5);
        MyArrayList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("asdf");
        myArrayList.add("uioyui");
        myArrayList.add("gf");
        myArrayList.add("jilkh");
        System.out.println("myArrayList.size() = " + myArrayList.size());
        System.out.println("myArrayList.get(0) = " + myArrayList.get(0));
        myArrayList.quickSort();
        System.out.println("myArrayList.quickSort() = " + myArrayList);
    }
}