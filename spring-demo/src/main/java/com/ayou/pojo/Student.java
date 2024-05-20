package com.ayou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    private String major;
    private Integer cid;
    private Integer age;
    private String password;


    public Student(Integer id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }
}
