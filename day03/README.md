# 第三天学习

## 1.利用工厂模式进行bean对象创建

### 工厂模式的两种类型

1.静态工厂模式：不需要创建类，但是可以直接调用工厂模式的方法。

2.抽象工厂模式：需要创建对象，然后调用对象的方法进行实例化创建

### 第一：创建两个工厂模式的类（使用静态方式）

静态工厂类

```java
package com.joy.bean.com.joy.factory;
import com.joy.bean.Person;
/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:38
 */
public class StaticObjFactory {
    public static Person getInter() {
        Person person = new Person();
        person.setAge(12);
        return person;
    }
}

```

抽象工厂模式类

```java
package com.joy.bean.com.joy.factory;

import com.joy.bean.Person;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:44
 */
public class ObjFactory {
    public Person getPersonObj() {
        Person person = new Person();
        person.setAge(12);
        return person;
    }
}

```

### 第二：ioc的配置

1.静态工厂类的ioc.xml配置

代码如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <!--利用工厂进行创建
    1.静态工厂类操作
    -->
    <bean id="person" class="com.joy.bean.com.joy.factory.StaticObjFactory" factory-method="getInter"></bean>
</beans>
```

关键就是使用factory-method这个使用工厂的方法。

测试类进行测试

```java
import com.joy.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:36
 */
public class MyTest {
    //测试类
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        //测试是否得到数据
        final Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }
}

```

可以得到person类静态工厂方式的操作。

### 第三：使用抽象工厂模式

ioc.xml的配置

关键

- 首先要创建工厂实例化的类的对象bean

- 然后使用factory-bean,factory-method这两个属性把对象引用进来，方法填进去就可以操作。

  代码如下：

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
  
      <!--利用工厂进行创建
      1.静态工厂类操作
      核心就是factory-method="getInter"
      -->
      <!--<bean id="person" class="com.joy.bean.com.joy.factory.StaticObjFactory" factory-method="getInter"></bean>-->
      <!--使用实例化工厂类的方式
      1.创建实例化工厂类
      2.使用factory-bean来引用实例化的类,在使用factory-method方法来进行操作
      -->
      <bean id="objFactory" class="com.joy.bean.com.joy.factory.ObjFactory"></bean>
      <bean id="person" class="com.joy.bean.Person" factory-bean="objFactory" factory-method="getPersonObj"></bean>
  </beans>
  ```

  

## 2.Bean对象的初始化和销毁

### 第一步：创建两个方法初始化和销毁的方法

在person中增加 init,destory这两个方法

```java
//初始化方法
    public void init(){
        System.out.println("调用初始化方法");
    }

    //销毁方法
    public void destory(){
        System.out.println("调用销毁方法");
    }
```



### 第二步：配置对象ioc文件

```xml
1.定义类
    destroy-method 初始化方法
    destroy-method 销毁方法
    -->
    <bean id="person" class="com.joy.bean.Person" init-method="init" destroy-method="destory"></bean>
```

运行的时候我发现只调用了初始化的方法，没有调用destory的方法，于是查看api说是要关闭容器的时候才能调用，于是我想使用context.close()方法来关闭，但是发现没有close方法，于是找父类找到了ClassPathXmlApplicationContext当中有close方法，最后强转一下就好了。

最后测试代码如下

```java
import com.joy.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:36
 */
public class MyTest {
    //测试类
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        //测试是否得到数据
        final Person person = context.getBean("person", Person.class);
        System.out.println(person);
        //要调用销毁方法
        ((ClassPathXmlApplicationContext) context).close();
    }
}

```

*注意：*

如果你的类单例的模式，或者说scope="prototype"单例模式的时候，他就不会调用销毁方法，如果是singleton的时候就会调用销毁方法，这个地方要注意。



## 3.bean对象初始化前后方法执行

*关键点：*

继承:BeanPostProcessor接口，它是调用初始化方法之前一个方法postProcessBeforeInitialization，之后一个方法postProcessAfterInitialization，原来的类如下：

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

public interface BeanPostProcessor {
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

```

第一步：继承BeanPostProcessor接口带入如下

```java
package com.joy.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 2:36
 */
public class InitBeanPostProcessor implements BeanPostProcessor {
    //这个地方是初始化之前的方法
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName);
        return bean;
    }

    //这个地方是初始化以后的方法
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName);
        return bean;
    }
}

```

第二步：ioc.xml进行配置，和普通的类没有啥区别。

```xml
<!--增加一个初始化前后的方法-->
    <bean id="beanpostProcessor" class="com.joy.bean.InitBeanPostProcessor"></bean>
```

## 4.bean引用第三方的类

*重点*

1.使用maven进行导入操作

2.把导入的jar包当成对应的类进行bean话就可以

3.写测试类

下面具体操作：阿里的数据库连接池druid来测试

(1)首先到maven里面找

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.22</version>
</dependency>

```

放入到pom.xml文件下

(2)然后在ioc.xml文件下使用

```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="username" value="root"></property>
        <property name="password" value="sanzhixiong"></property>
    </bean>
```

(3)写测试类

```java
final DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource);
```

## 5.spring加载外部配置文件

*作用*

替换某一些数据的配置

1. 在resources文件下创建一个配置文件
2. 使用context，ioc文件命名空间
3. 需要在ioc文件下进行加载对应的文件
4. 使用${}进行文字的匹配

在resource中添加dbconfig.properties

然后在里面写入对应的key value的数据键值对

```properties
jdbc.username=root
jdbc.password=123456
```

然后在ioc.xml使用命名空间操作

```xml
xmlns:contest="http://www.springframework.org/schema/context"
http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd

```

加入这三句，spring的官网有具体的说明，不行直接百度也有。

```xml
<!--增加了一句外部配置文件-->
    <contest:property-placeholder location="dbconfig.properties"/>
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="username" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
```

然后三四不一起执行操作。

最后写一个测试类进行测试

```java
import com.alibaba.druid.pool.DruidDataSource;
import com.joy.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:36
 */
public class MyTest {
    //测试类
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        //测试是否得到数据
//        final Person person = context.getBean("person", Person.class);
//        System.out.println(person);
        final DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource.getUsername());
        //要调用销毁方法
        ((ClassPathXmlApplicationContext) context).close();
    }
}
```

## 6、spring基于xml文件的自动装配

​		当一个对象中需要引用另外一个对象的时候，在之前的配置中我们都是通过property标签来进行手动配置的，其实在spring中还提供了一个非常强大的功能就是自动装配，可以按照我们指定的规则进行配置，配置的方式有以下几种：

​		default/no：不自动装配

​		byName：按照名字进行装配，以属性名作为id去容器中查找组件，进行赋值，如果找不到则装配null

​		byType：按照类型进行装配,以属性的类型作为查找依据去容器中找到这个组件，如果有多个类型相同的bean对象，那么会报异常，如果找不到则装配null

​		constructor：按照构造器进行装配，先按照有参构造器参数的类型进行装配，没有就直接装配null；如果按照类型找到了多个，那么就使用参数名作为id继续匹配，找到就装配，找不到就装配null

ioc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="com.mashibing.bean.Address">
        <property name="province" value="河北"></property>
        <property name="city" value="邯郸"></property>
        <property name="town" value="武安"></property>
    </bean>
    <bean id="person" class="com.mashibing.bean.Person" autowire="byName"></bean>
    <bean id="person2" class="com.mashibing.bean.Person" autowire="byType"></bean>
    <bean id="person3" class="com.mashibing.bean.Person" autowire="constructor"></bean>
</beans>
```

## 7、SpEL的使用

​		SpEL:Spring Expression Language,spring的表达式语言，支持运行时查询操作对象

​		使用#{...}作为语法规则，所有的大括号中的字符都认为是SpEL.

ioc.xml

```xml
    <bean id="person4" class="com.mashibing.bean.Person">
        <!--支持任何运算符-->
        <property name="age" value="#{12*2}"></property>
        <!--可以引用其他bean的某个属性值-->
        <property name="name" value="#{address.province}"></property>
        <!--引用其他bean-->
        <property name="address" value="#{address}"></property>
        <!--调用静态方法-->
        <property name="hobbies" value="#{T(java.util.UUID).randomUUID().toString().substring(0,4)}"></property>
        <!--调用非静态方法-->
        <property name="gender" value="#{address.getCity()}"></property>
    </bean>
```

