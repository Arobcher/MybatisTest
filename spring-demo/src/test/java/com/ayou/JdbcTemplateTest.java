package com.ayou;

import com.ayou.pojo.Student;
import com.ayou.service.StudentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;
import java.util.Scanner;

public class JdbcTemplateTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService studentService = classPathXmlApplicationContext.getBean(StudentService.class);

        System.out.println("欢迎来到学生管理系统");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入"+username+"的密码:");
        String password = scanner.nextLine();

        Optional<Student> student = studentService.login(username, password);
        if (student.isPresent()) {
            System.out.println("用户登陆成功！");
            System.out.println(username+"是"+student.get().getMajor()+"班的!");
        } else {
            System.out.println("登录失败, 用户名或密码错误");
        }

    }
}
