package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Slf4j
@RestController
// 用在请求的类上，表示对类的说明,tags="说明该类的作用，可以在UI界面上看到的注解"
@Api(tags = "HelloWorld 相关 API")
public class HelloWorld {

    // 通过LoggerFactory获取Logger实例, 由 lombok 实现的@Slf4j也可以，二选一即可
    private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

    /**
     * header-->请求参数的获取：@RequestHeader
     * query-->请求参数的获取：@RequestParam
     * path（用于restful接口）-->路径参数（属于 URI 的一部分）的获取：@PathVariable
     * body：@RequestBody，支持面向对象映射
     * form：get-->@RequestParam,post-->@RequestBody
     */
    // @ApiOperation：用在请求的方法上，说明方法的用途、作用
    @ApiOperation(value = "hello说明方法的用途、作用", notes = "方法的备注说明")
    // @ApiImplicitParams：用在请求的方法上，表示一组参数说明
    // dataType：参数类型，默认String，其它值dataType="Integer"
    // defaultValue：参数的默认值
    // required：参数是否必须传
    // name：参数名
    // value：参数的汉字说明、解释
    @ApiImplicitParams({@ApiImplicitParam(name="name", value = "wl",required = true, paramType = "path")})
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) {
        return "hello "+name+"你好";
    }

    /**
     * Field car in com.atguigu.boot.controller.HelloWorld required a bean of type
     * 'com.atguigu.boot.bean.Car' that could not be found.
     *
     * The injection point has the following annotations:
     * 	- @org.springframework.beans.factory.annotation.Autowired(required=true)
     *
     * 	Consider defining a bean of type 'com.atguigu.boot.bean.Car' in your configuration.
     *
     * 	启动容器报错，依赖注入失败
     */
    @Autowired
    private Car car;

    @ApiOperation(value = "car")
    // @RequestMapping(value = "/car") 默认 get 请求
    @GetMapping("/car6")
    public Car car() {
        log.info("car:{}", car);
        return car;
    }
}
