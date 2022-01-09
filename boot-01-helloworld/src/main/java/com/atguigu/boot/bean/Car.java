package com.atguigu.boot.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 如果car 是第三方的类，无法标注为@Component，那就在配置类中使用@EnableConfigurationProperties注入
 */
//@Component
@ConfigurationProperties(prefix = "car")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "car 响应体")
public class Car {

    @ApiModelProperty(name = "品牌", value = "BYD")
    private String branch;

    @ApiModelProperty(name = "价格", value = "100000")
    private Integer price;
}
