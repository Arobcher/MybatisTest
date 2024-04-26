package com.ayou;

import com.ayou.pojo.User;
import com.ayou.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanTest {

    ApplicationContext context;
    @Before
    public void onInit(){
        this.context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void beanTest() {
        User zhangsan = context.getBean("zhangsan", User.class);
        User lisi = context.getBean("lisi", User.class);

        zhangsan.show();
        lisi.show();
    }

    @Test
    public void userServiceAopTest() {
        UserService userService = context.getBean(UserService.class);
        User zhangsan = context.getBean("zhangsan", User.class);
        userService.deleteUser(zhangsan);
        userService.deleteUserException(zhangsan);
        /* Result
          环绕增强处理, 目标方法: deleteUser
          环绕增强处理: 执行前
          前置增强处理
          成功删除User 张三
          后置增强处理
          环绕增强处理: 执行后
          异常增强处理: 在目标后方抛出异常后执行
          异常信息: not found user
          java.lang.NullPointerException: not found user
         */
    }
}
