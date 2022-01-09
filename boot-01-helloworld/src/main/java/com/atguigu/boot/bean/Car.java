package com.atguigu.boot.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.yaml.snakeyaml.nodes.Tag.PREFIX;

/**
 * 如果car 是第三方的类，无法标注为@Component，那就在配置类中使用@EnableConfigurationProperties注入
 */
//@Component
// 开启配置注入并且把 Car 组件注册到容器中
@ConfigurationProperties(value = Car.PREFIX)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "car 响应体")
public class Car {

    public static final String PREFIX = "car";

    @ApiModelProperty(name = "品牌", value = "BYD")
    private String branch = "KIM";

    @ApiModelProperty(name = "价格", value = "100000")
    private Integer price = 200000;
}
