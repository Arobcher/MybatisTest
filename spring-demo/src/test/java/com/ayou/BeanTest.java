package com.ayou;

import com.ayou.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
}
