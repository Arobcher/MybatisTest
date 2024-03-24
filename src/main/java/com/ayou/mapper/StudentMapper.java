package com.ayou.mapper;

import com.ayou.model.Student;

import java.util.List;

public interface StudentMapper {
    Student selectStudent(Student student);
    List<Student> selectStudentsByIdLessThan(int maxId);
}
