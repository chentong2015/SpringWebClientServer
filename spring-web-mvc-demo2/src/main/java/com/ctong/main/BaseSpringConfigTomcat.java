package com.ctong.main;

/**
 * 集成Tomcat Web Container
 * 1. Settings > Plugins > Tomcat and TomEE Integration (TomEE is for Java EE Web Stack built on top of Tomcat)
 * 2. Settings > Build > Application Server > Tomcat (tomcat 8.5 注意使用8.5版本来部署) !!!
 * .
 * 导入Eclipse project
 * 1. Settings > Plugins > Eclipse Integration / Spring 相关的所有插件都需要安装
 * 2. 选择项目文件下面的 .project 加载
 * 3. 确定eclipse project local repository的位置, 和IDEA使用的位置一致
 * .
 * 构建项目的artifact打包文件
 * 1. Project Structure > Artifacts > 确定war文件的名称和生成的具体位置路径 !!!
 * 2. Jar File can not deploy to Tomcat web container 不能部署.jar的文件 !!!
 * 3. 直接使用maven lifecycle package来进行打包，生成指定的war文件 !!!
 * .
 * . 配置启动Run/Debug Configurations
 * 1. Add Tomcat Server > Local > Add artifacts 添加指定要运行的war文件(项目编译和打包好的文件)
 */
public class BaseSpringConfigTomcat {
    
    /**
     * 不使用Maven构建Spring MVC project项目结构
     * - lib
     * - src
     * --- com.springmvc.demo
     * ------ main.java
     * - web
     * --- WEB-INF
     * ------ views
     * --------- index.jsp
     * ------ applicationContext.xml
     * ------ springMvcDemo-servlet.xml
     * ------ web.xml
     */
}
