# 注解的基础运用

## 增加测试类的Junit进行项目的操作

在maven中直接引用

```xml
<!-- https://mvnrepository.com/artifact/junit/junit -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>
```

测试的方法在Test文件下建立一个文件myTest

```java
import org.junit.Test;
/**
 * @author joy
 * @date 2020/5/31
 */
public class MyTest {
  //增加了Test的一个注解
    @Test
    public void test01(){
        System.out.println("测试");
    }
}
```

## 使用注解的方式进行baen对象创建

1.创建xml文件下进行增加

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:contest="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!--增加扫描的包-->
    <contest:component-scan base-package="com.joy"></contest:component-scan>
</beans>
```

2.注册的四个

- @Server:业务逻辑，推荐给业务逻辑层添加此注解
- @Repository:仓库管理，推荐给数据访问层添加此注解
- @Controller:控制器，推荐给controller层添加此注解
- @Component:给不属于以上基层的组件添加此注解

3.创建一个PersonController类

```java
package com.joy.controller;

import org.springframework.stereotype.Controller;
/**
 * @author joy
 * @date 2020/5/31
 */
@Controller
public class PersonController {
}
```

测试类

```java
import com.joy.controller.PersonController;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @date 2020/5/31
 */
public class MyTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
    @Test
    public void test01(){
        //基础的使用
        PersonController personController = context.getBean("personController",PersonController.class);
        System.out.println(personController);
    }
}
```