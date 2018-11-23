package com.barry.java.Generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {

    public static void main (String args[]) {
        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Number> number = new ArrayList<>();

        name.add("icon");
        age.add(18);
        number.add(344);

        getData(name);
        getData(age);
        getData(number);

//        getUperNumber(name);//1
        getUperNumber(age);//2
        getUperNumber(number);//3
    }

    public static void getData(List<?> data) {
        System.out.println("data:" + data.get(0));
    }

    public static void getUperNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));
    }
}
