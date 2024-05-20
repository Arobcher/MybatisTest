package com.ayou.service;

import com.ayou.pojo.Student;
import com.ayou.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> login(String userName, String password) {
        Student student = jdbcTemplate.queryForObject("select * from student where name = ? and password = ?", new Object[]{userName, password},
                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getString("major")));
        return Optional.ofNullable(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
