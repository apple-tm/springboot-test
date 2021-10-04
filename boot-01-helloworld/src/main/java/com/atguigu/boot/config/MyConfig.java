package com.atguigu.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 1.配置类，@Bean 默认方法名为组件名，默认组件为单实例
 * 2.配置类本身也是组件
 * 3.proxyBeanMethods:spring5.2之后功能
 *  3.1 组件中没有依赖关系使用 lite 模式（proxyBeanMethods=false）加速容器启动速度，
 *      减少判断（容器中是否存在依赖组件的判断）
 *  3.2 组件之间存在依赖关系使用 full 模式(proxyBeanMethods=true)，保证单实例
 *  3.3 默认配置为 true，但其实没有组件依赖应该设置为 false
 * 4.@Import: 默认导入 ID 为全类名的组件到容器中
 */
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true) //配置类， 对应 bean.xml 配置文件的功能
public class MyConfig {

    /**
     * 外部类使用 MyConfig 对象无论调用多少次 user01 方法，默认都是返回单实例对象
     * @return
     */
    @Bean// 向spring容器中注入ID 为方法名的单实例User对象
    public User user01() {
        User user = new User("jack", 18);
        user.setPet(tom());
        return user;
    }

    @Bean("tom01") // 向 spring 容器注入 ID 为@Bean 注解参数的单实例 User 对象
    public Pet tom() {
        return new Pet("tom01");
    }

}
