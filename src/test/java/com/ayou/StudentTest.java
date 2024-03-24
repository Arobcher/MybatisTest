package com.ayou;

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
            Student codeQuery = studentMapper.selectStudent(new Student(null, null, "114514", null));
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

    private void openSession(BiConsumer<SqlSession, StudentMapper> sessionConsumer) {
        @Cleanup SqlSession session = sqlSessionFactory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        sessionConsumer.accept(session, mapper);
    }
}
