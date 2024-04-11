package com.ayou.dto;

import com.ayou.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassDto {
    private Integer id;
    private String name;
    private List<Student> students; // 一对多的关系
}
