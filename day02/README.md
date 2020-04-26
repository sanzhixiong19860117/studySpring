# 第二天学习

## Spring HelloWorld maven使用

- **创建maven项目**

  首先要去安装maven这个可以去百度，百度比我说的更清楚。
- **去找到maven的对应jar包**
	https://mvnrepository.com/artifact/org.springframework/spring-context/5.2.3.RELEASE

- **添加对应的pom依赖**

  pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <groupId>com.mashibing</groupId>
      <artifactId>spring_demo</artifactId>
      <version>1.0-SNAPSHOT</version>
  
      <dependencies>
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-context</artifactId>
              <version>5.2.3.RELEASE</version>
          </dependency>
      </dependencies>
  </project>
  ```

然后编辑器会自动的下载5.2.3.RELEASEjar对应的包。

## 搭建spring项目需要注意的点：

​		1、一定要将配置文件添加到类路径中，使用idea创建项目的时候要放在resource目录下

​		2、导包的时候别忘了commons-logging-1.2.jar包

​		**细节点：**

​		1、ApplicationContext就是IOC容器的接口，可以通过此对象获取容器中创建的对象

​		2、对象在Spring容器创建完成的时候就已经创建完成，不是需要用的时候才创建

​		3、对象在IOC容器中存储的时候都是单例的，如果需要多例需要修改属性

​		4、创建对象给属性赋值的时候是通过setter方法实现的

​		5、对象的属性是由setter/getter方法决定的，而不是定义的成员属性

## 使用注意点：

### 1.通过id

```java
final Person person = context.getBean("person", Person.class);
```

因为id是唯一的操作，所以推荐这种方式

### 2.通过类型

```java
final Person person = context.getBean(Person.class);
```

不是很推荐这种方式，这个和css 的class类型一样，如果在bean文件有同一个类的多个标签的时候没有办法确认是那个类。

### 3.bean的configxml文件下，是通过set/get方法来进行对属性设置数值的。

### 4.一定要在对应的类当中写下空的构造函数，因为没有的话，没有办法创建对象，实现的方式是用了反射的操作。

### 5.使用constructor-arg 来创建属性的数据。

```xml
<bean id="person" class="com.joy.bean.Person">
        <constructor-arg name="id" value="1"></constructor-arg>
        <constructor-arg name="name" value="sanzhixiong"></constructor-arg>
        <constructor-arg name="age" value="22"></constructor-arg>

    </bean>
```

这个和构造函数的参数一一对象的

看下代码：

```java
package com.joy.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 15:36
 */
public class Person {
    private int id;
    private String name;
    private int age;

    public Person() {
    }

    //这个地方和对应的constructor-arg和这个构造函数必须要一样 
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

```

问题1：如果是多个构造函数参数不一样的操作。

答：他会根据你的构造器创建以后进行匹配，入下代码

```xml
<bean id="person" class="com.joy.bean.Person">
        <constructor-arg value="1"></constructor-arg>
        <constructor-arg value="sanzhixiong"></constructor-arg>
        <constructor-arg value="22"></constructor-arg>
    </bean>
```

我们可以看到他和我们的构造函数进行匹配

```java
public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
```

如果我们对构造函数重写了

```java
public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("第二个构造函数");
    }

    public Person(int id, int age) {
        this.id = id;
        this.age = age;
        System.out.println("第一个构造函数");
    }
```

通过学习，发现jdk12以后必须要对每一个构造器都需要完全的匹配才能运行

```xml
<bean id="person" class="com.joy.bean.Person">
        <constructor-arg name="id" value="1"></constructor-arg>
        <!--<constructor-arg value="sanzhixiong"></constructor-arg>-->
        <constructor-arg value="22"></constructor-arg>
    </bean>
```

### 6.定义复杂类型

1.常用的数组的方式

使用array标签引入数组类型的数据

使用ref引用对象

map，set，list等基础的运用

```java
package com.joy.bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 15:36
 */
public class Person {
    private int id;
    private String name;
    private int age;
    //数字的运用
    private String[] hobbies;
    //list的操作
    private List<String> list;
    //增加复杂的操作
    private List<Address> addList;

    public List<Address> getAddList() {
        return addList;
    }

    public void setAddList(List<Address> addList) {
        this.addList = addList;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("第二个构造函数");
    }

    public Person(int id, int age) {
        this.id = id;
        this.age = age;
        System.out.println("第一个构造函数");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", list=" + list +
                ", addList=" + addList +
                '}';
    }
}

```

对应的配置文件

```xml
<bean id="person" class="com.joy.bean.Person">
        <property name="id" value="1"></property>
        <property name="age" value="20"></property>
        <property name="name" value="lisan"></property>
        <!--第一种方式执行操作-->
        <!--<property name="hobbies" value="hello,world,sanzhixiong"></property>-->
        <property name="hobbies">
            <array>
                <value>hello</value>
                <value>world</value>
                <value>sanzhixiong</value>
            </array>
        </property>
        <!--list基础操作-->
        <property name="list">
            <list>
                <value>hello</value>
                <value>world</value>
                <value>sanzhixiong</value>
            </list>
        </property>
        <property name="addList">
            <list>
                <bean class="com.joy.bean.Address">
                    <property name="town" value="宜昌"></property>
                </bean>
            </list>
        </property>
    </bean>
```

### 7.继承关系

```xml
<bean id="person" class="com.joy.bean.Person">
        <property name="id" value="1"></property>
        <property name="name" value="zhangsan"></property>
        <property name="age" value="21"></property>
    </bean>
    <!--parent:指定bean的配置信息继承于哪个bean-->
    <bean id="person2" class="com.joy.bean.Person" parent="person">
        <property name="name" value="lisi"></property>
    </bean>
```

使用抽象类的关键也可以在这里进行不能实例化的类abstract操作

```xml
<bean id="person" class="com.joy.bean.Person" abstract="true">
        <property name="id" value="1"></property>
        <property name="name" value="zhangsan"></property>
        <property name="age" value="21"></property>
    </bean>
    <!--parent:指定bean的配置信息继承于哪个bean-->
    <bean id="person2" class="com.joy.bean.Person" parent="person">
        <property name="name" value="lisi"></property>
    </bean>
```

加了abstract=true以后我们运行主函数

```java
ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        //注意点
        //通过id
//        final Person person = context.getBean("person", Person.class);
        //通过类型来查找
        final Person person2 = context.getBean("person", Person.class);
        System.out.println(person2);
```

会出现报错

Exception in thread "main" org.springframework.beans.factory.BeanIsAbstractException: Error creating bean with name 'person': Bean definition is abstract
	at org.springframework.beans.factory.support.AbstractBeanFactory.checkMergedBeanDefinition(AbstractBeanFactory.java:1412)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:298)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:207)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1114)
	at com.test.MyTest.main(MyTest.java:20)