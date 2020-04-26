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