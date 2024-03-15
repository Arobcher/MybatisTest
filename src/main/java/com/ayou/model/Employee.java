package com.ayou.model;

import lombok.Data;

@Data
public class Employee {

    public Employee(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    private int id;
    private String name;
    private int age;
    private String position;
}
