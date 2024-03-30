package com.blogapp52;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee(1, "mike", 5000),
                new Employee(2, "smith", 6000),
                new Employee(3, "mike", 7000)
        );
        Map<String, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e -> e.getName()));
        System.out.println(newData);
    }
}