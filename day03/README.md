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

  



