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