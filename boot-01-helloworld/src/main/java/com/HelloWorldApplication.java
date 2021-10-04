package com;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        // 1.SpringApplication.run 返回 IOC 容器上下文
        ConfigurableApplicationContext cac = SpringApplication.run(HelloWorldApplication.class, args);

        // 2.遍历容器中生成的组件
        String[] beans = cac.getBeanDefinitionNames();
        for (String str : beans) {
            System.out.println(str);
        }

        // 3. 从容器中获取组件
        Pet pet1 = cac.getBean("tom01", Pet.class);
        Pet pet2 = cac.getBean("tom01", Pet.class);

        // 4.判断多次获取相同组件是否是单实例
        System.out.println(pet1 == pet2);

        // proxyBeanMethods默认为 true：此时的配置类为 cglib 代理增强的 com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$aac9f98f@474749b8
        // 使用配置类组件获取组件从容器中获取组件
        // proxyBeanMethods改为 false：com.atguigu.boot.config.MyConfig@51ff3c4b
        // 使用配置类组件获取组件从新 new 对象
        // 注意：使用配置类组件获取组件才有效，proxyBeanMethods属性不会对 getBean 获取组件有效
        MyConfig myConfig = cac.getBean(MyConfig.class);
        System.out.println(myConfig);
        User user1 = cac.getBean("user01", User.class);
        User user2 = myConfig.user01();
        // 5.验证使用配置类获取组件是否会打破单例，默认不会,proxyBeanMethod默认为 true，保持单实例
        System.out.println(user1 == user2);

        // 6.判断用户组件依赖的宠物组件是否是容器中的
        System.out.println(user2.getPet() == myConfig.tom());

        System.out.println("========");
        String[] beanNamesForType = cac.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        System.out.println(cac.getBean(DBHelper.class));
    }
}

