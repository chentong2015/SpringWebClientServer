package com.spring.mvc;

// TODO: 参考Spring框架官方文档 The Resource Interface
// https://docs.spring.io/spring-framework/docs/3.0.0.M4/reference/html/ch04s07.html
public class SpringResourcePath {

    /**
     * FileSystem 使用文件系统路径来加载xml配置文件，从项目root路径下面找相对路径  ===> 添加classpath:xxx前缀将会改成通过classpath来查找
     * 1. classpath:  只会到你的class路径中查找找文件
     * 2. classpath*: 不仅包含class路径，还包括jar文件中(class路径)进行查找
     */
    // ConfigurableApplicationContext contextFileSystem = new FileSystemXmlApplicationContext("beans.xml");

    /**
     * FileSystemResources 从项目root路径下面找相对路径                       ===> 添加file:前缀将改成通过filesystem来查找
     *  1. file:beans.xml 放置在项目root目录
     *  2. file:src/main/resources/beans.xml 放置在resources资源目录中
     */
    // ConfigurableApplicationContext contextFileSystemResources = new ClassPathXmlApplicationContext("file:src/main/resources/beans.xml");

    /**
     * ClassPath：从classpath加载配置文件                                    ===> resources目录是默认的classpath !!
     * 1. "beans.xml"  直接使用相对的位置路径                                 ===> 该路径必须是在Child Module, 不能是在Root Module !!
     * 2. "classpath:beans.xml"
     */
    // ConfigurableApplicationContext contextClassPath = new ClassPathXmlApplicationContext("beans.xml");

    /**
     * ClassPath 使用ClassPath路径
     * 1. "beans.xml"  直接使用相对的位置路径
     * 2. "classpath:beans.xml"
     */
    // ConfigurableApplicationContext contextGeneric = new GenericXmlApplicationContext("classpath:beans.xml");
}
