package com.blogapp52;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("prem");
        
        List<String> list2 = new ArrayList<>();
        list2.add("sharma");

        ArrayList<String> concatenatedList = new ArrayList<>(list1);
        concatenatedList.addAll(list2);

        System.out.println(concatenatedList);

    }
}