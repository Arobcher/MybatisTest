package com.ayou;

import com.ayou.mapper.EmployeeMapper;
import com.ayou.model.Employee;
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
import java.util.function.BiConsumer;

public class EmployeeTest {

    @SneakyThrows
    @Before
    public void init() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    SqlSessionFactory sqlSessionFactory;
    @Test
    public void getEmployee() {
        this.openSession(((session, employeeMapper) -> {
            Employee blog = employeeMapper.selectEmployee(1);
            Assert.assertNotNull(blog);
        }));
    }


    @Test
    public void updateEmployee() {
        this.openSession((session, mapper) -> {
            Employee employee = mapper.selectEmployee(1);
            employee.setAge(20);
            int updateEmployee = mapper.updateEmployee(employee);
            session.commit();
            Assert.assertEquals(1, updateEmployee);
            Assert.assertEquals(21, employee.getAge());
        });
    }

    @Test
    public void insertEmployee() {
        this.openSession(((session, employeeMapper) -> {
            Employee employee = new Employee("Test", 19, "Member");
            employeeMapper.insertEmployee(employee);
            session.commit();
            Assert.assertTrue(employee.getId() > 0);
        }));
    }

    @Test
    public void deleteEmployee() {
        this.openSession(((session, employeeMapper) -> {
            int i = employeeMapper.deleteEmployee(7);
            session.commit();
            Assert.assertEquals(1, i);
        }));
    }

    private void openSession(BiConsumer<SqlSession, EmployeeMapper> sessionConsumer) {
        @Cleanup SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        sessionConsumer.accept(session, mapper);
    }
}
