package com.atguigu.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Car;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1.配置类，@Bean 默认方法名为组件名，默认组件为单例模式
 * 2.配置类本身也是组件
 * 3.proxyBeanMethods:spring5.2之后功能
 *  3.1 组件中没有依赖关系使用 lite 模式（proxyBeanMethods=false）加速容器启动速度，
 *      减少判断（容器中是否存在依赖组件的判断）
 *  3.2 组件之间存在依赖关系使用 full 模式(proxyBeanMethods=true)，保证单实例
 *  3.3 默认配置为 true，但其实没有组件依赖应该设置为 false
 */

// @Import: 默认导入 ID 为全类名的组件到容器中
@Import({User.class, DBHelper.class})
// 配置类，参数proxyBeanMethods:spring5.2之后功能，分为 full 和 lite mode
@Configuration(proxyBeanMethods = true)
// 配置类中的组件注入容器的顺序:从上到下
@ConditionalOnMissingBean(name = "tom")
// 用于兼容老式 spring 项目，可以将 bean 配置文件中配置组件加载到容器中
@ImportResource("classpath:beans.xml")
// 开启配置注入并且把 Car 组件注册到容器中
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    @Bean("tom01")
    public Pet tom() {
        return new Pet("tom01");
    }

    /**
     * @ConditionalOnBean:在容器中存在tom01组件的前提下才向容器中注入下面组件（组件 ID 和 name 是同一个概念）
     * @ConditionalOnBean:如果依赖的组件在本组件之后才注入到容器中，会判断为没有该 bean，注入顺序为按代码从上至下
     * multipartResolver：@Bean 修饰的方法中如果带有参数，那么参数会从容器中找并指向参数引用
     */
    @ConditionalOnBean(name = "tom01")
    @Bean
    public User user01() {
        User user = new User("jack", 18);
        user.setPet(tom());
        return user;
    }
}
