package com.ayou.mapper;

import com.ayou.dto.ClassDto;
import com.ayou.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    Student selectStudent(Student student);
    List<Student> selectStudentsByIdLessThan(int maxId);

    // ID查询
    @Select("SELECT * FROM test.student WHERE id = #{id}")
    Student findById(int id);

    // 更新年龄和姓名
    @Update("UPDATE test.student SET name = #{name}, age = #{age} WHERE id = #{id}")
    void updateStudent(@Param("id") int id, @Param("name") String name, @Param("age") int age);

    // 一对多查询
    @Select("SELECT * FROM test.c_class WHERE classname=#{name}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "students", column = "id",
                    many = @Many(select = "selectStudentsByClassId"))
    })
    ClassDto selectClassWithStudentsByName(String name);

    @Select("SELECT * FROM test.student WHERE cid = #{classId}")
    List<Student> selectStudentsByClassId(int classId);
}
