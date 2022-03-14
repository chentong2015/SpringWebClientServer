package main;

// Spring Web MVC:
// 1. based on Servlet 3.1 API
// 2. uses thread pools to implement asynchronous Java web app
// 3. app can be run on Servlet 3.1+ containers such as Tomcat 8.5 and Jetty 9.3
public class BaseSpringMVC {

    // http://maven.apache.org/plugins/maven-war-plugin/usage.html
    /**
     * 添加 Maven War Plugin
     * War Plugin 要求web.xml文件必须存在(因为涉及到DispatcherServlet的配置和注册) !!
     * 1. War: Web Application Archive / Jar: Java Archive File
     * 2. War File contains the resources necessary for web application 包含应用程序的依赖和必要资源
     * 3. War Plugin collects all artifact dependencies, classes and resources of the web com.ctong.springboot.config and packages them into war file
     */

    /**
     * 添加/webapp:
     * Web App Resources 资源的默认路径设置：Project Structure > Facets > Web > Web resource Directories
     * 1. 文件夹的名称由Maven standard directory标准目录定义
     * 2. 文件夹中放置application source files, html pages, css, javascript
     * 3. /webapp/index.html 默认的web初始页面  ====> main/resources/templates/ 等效的默认位置 !!!
     * .
     * 添加/webapp/WEB-INF:
     * 1. Contains all things related to the com.ctong.springboot.config that are not in the document root of the application 这个目录不属于应用程序的公共文件Tree !!
     * 2. 这里面的文件不能直接served to client by the container, 但是这里面的文件对于Server code是可见的
     * 3. 可以在目录中创建新的目录/webapp/WEB-INF/views, 暴露views by dispatcher server
     * ===> 使用Controller来access
     */

    // Tomcat Configuration 官方配置 https://cwiki.apache.org/confluence/display/TOMCAT/FAQ
    // Maven Cargo Plugin   官方文档 https://codehaus-cargo.github.io/cargo/Tomcat+10.x.html
    /**
     * 添加 Cargo Plugin
     * 1. Cargo is a thin wrapper that allows you to manipulate various type of application containers in a standard way
     * 2. 可将Web Container嵌入(不必本地部署安装)在Cargo Plugin插件中，通过插件指令操作Container
     * .
     * 1. 运行War插件action   > war:war 在/target下生成指定.war文件 spring-mvc-1.0-SNAPSHOT.war
     * ====> 直接使用mvn:clean install来构建 !!
     * 2. 运行Cargo插件action > cargo:run 启动所包装的容器, 并将项目(.war文件)部署到container Tomcat 10.x Embedded
     * 3. 访问路径 localhost:8080/project-name | module-name/index.html
     */
}
