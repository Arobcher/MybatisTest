package com.ayou;

import com.ayou.dto.ClassDto;
import com.ayou.mapper.EmployeeMapper;
import com.ayou.mapper.StudentMapper;
import com.ayou.model.Employee;
import com.ayou.model.Student;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.function.BiConsumer;

public class StudentTest {

    @SneakyThrows
    @Before
    public void init() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    SqlSessionFactory sqlSessionFactory;

    /**
     * 多条件查询学生
     */
    @Test
    public void getEmployee() {
        this.openSession(((session, studentMapper) -> {
            Student nameQuery = studentMapper.selectStudent(new Student(null, "Ayou"));
            Assert.assertNotNull(nameQuery);
            Student manjorQuery = studentMapper.selectStudent(new Student((Integer) null, null, "ComputerScience"));
            Assert.assertNotNull(manjorQuery);
            Student codeQuery = studentMapper.selectStudent(new Student(null, null, "114514", null, null, null));
            Assert.assertNotNull(codeQuery);
        }));
    }

    /**
     * 查询小于5的学生
     */
    @Test
    public void queryStudents() {
        this.openSession(((session, studentMapper) -> {
            List<Student> students = studentMapper.selectStudentsByIdLessThan(5);
            Assert.assertEquals(4, students.size());
        }));
    }

    /**
     * 根据ID查询学生信息
     */
    @Test
    public void findById() {
        this.openSession(((sqlSession, studentMapper) -> {
            Student byId = studentMapper.findById(2);
            System.out.println(byId);
            Assert.assertNotNull(byId);
        }));
    }

    /**
     * 更新学生姓名和年龄
     */
    @Test
    public void updateStudentNameAndAge() {
        this.openSession((sqlSession, studentMapper) -> {
            studentMapper.updateStudent(1, "李雷", 21);
            sqlSession.commit();
            Student student = studentMapper.findById(1);
            Assert.assertEquals("李雷", student.getName());
        });
    }

    /**
     * 一对多查询二班所有学生信息
     */
    @Test
    public void queryClassStudents() {
        this.openSession((sqlSession, studentMapper) -> {
            ClassDto students = studentMapper.selectClassWithStudentsByName("二班");
            System.out.println(students.getName() + students.getStudents());
        });
    }

    private void openSession(BiConsumer<SqlSession, StudentMapper> sessionConsumer) {
        @Cleanup SqlSession session = sqlSessionFactory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        sessionConsumer.accept(session, mapper);
    }
}