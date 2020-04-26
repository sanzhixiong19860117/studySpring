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

