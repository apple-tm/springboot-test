package com.atguigu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @SpringBootConfiguration 代表主程序也是一个配置类
 *
 * @EnableAutoConfiguration core 需学习源码
 *  1.@AutoConfigurationPackage：这个组合注解主要是@Import(AutoConfigurationPackages.Registrar.class)，
 *  它通过将Registrar类导入到容器中，而Registrar类作用是扫描主配置类同级目录以及子包，并将相应的组件导入到
 *  springboot创建管理的容器中
 *  2.@Import(AutoConfigurationImportSelector.class)：它通过将AutoConfigurationImportSelector类
 *  导入到容器中，AutoConfigurationImportSelector类作用是通过selectImports方法执行的过程中，会使用内部
 *  工具类SpringFactoriesLoader，查找classpath上所有jar包中的META-INF/spring.factories进行加载，实现
 *  将配置类信息交给SpringFactory加载器进行一系列的容器创建过程，自动配置，按需加载（框架开关，@ConditionOnClass
 *  某些类文件存在也就是添加了 maven 依赖）
 *
 * @ComponentScan 指定扫描包，如果放在excludeFilters里,会过滤掉TypeExcludeFilter过滤的类-----即不扫描
 */
@SpringBootApplication
// 开启 swagger3
@EnableOpenApi
public class HelloWorldApplication {

    public static void main(String[] args) {
        // 1.SpringApplication.run 返回 IOC 容器上下文
        ConfigurableApplicationContext cac = SpringApplication.run(HelloWorldApplication.class, args);

        // 2.遍历容器中生成的组件,随着依赖框架的增加，容器中的 bean 会不断增加
//        String[] beans = cac.getBeanDefinitionNames();
//        for (String str : beans) {
//            System.out.println(str);
//        }

          // 3. 从容器中获取组件,无论是 full 模式还是 lite 模式都返回 true
//        Pet pet1 = cac.getBean("tom01", Pet.class);
//        Pet pet2 = cac.getBean("tom01", Pet.class);
//        System.out.println(pet1 == pet2);

        // 4. 配置类的 proxyBeanMethods 属性默认为 true，此时执行配置类中方法为 cglib 代理增强的
        // com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$aac9f98f@474749b8
        // 使用配置类构造组件的方法获取组件会检查容器中是否存在，存在就返回
        // proxyBeanMethods改为 false：com.atguigu.boot.config.MyConfig@51ff3c4b
        // 使用配置类组件构造方法获取组件会新 new 的对象返回
        // 主要处理组件依赖问题，如果组件之间存在依赖关系使用 full 模式，否则使用 lite mode
//        MyConfig myConfig = cac.getBean(MyConfig.class);
//        User user1 = cac.getBean("user01", User.class);
//        User user2 = myConfig.user01();
//        User user3 = myConfig.user01();
//        System.out.println(user1 == user2);
//        System.out.println(user3 == user2);
//        System.out.println(user2.getPet() == myConfig.tom());

        // 5.三个同类型的 bean 被加载，@Bean、@Import和@ImportResource注入 bean 是区分开的
//        String[] beanNamesForType = cac.getBeanNamesForType(User.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }

        // 6.输出容器中的 bean 数量
//        int beanDefinitionCount = cac.getBeanDefinitionCount();
//        System.out.println("The number of bean = " + beanDefinitionCount);
    }
}

