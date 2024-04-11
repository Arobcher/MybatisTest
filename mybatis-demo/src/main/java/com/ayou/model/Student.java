package com.ayou.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(Integer id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    public Student(String name, String code, String major) {
        this.name = name;
        this.code = code;
        this.major = major;
    }

    private Integer id;
    private String name;
    private String code;
    private String major;
    private Integer cid;
    private Integer age;
}
