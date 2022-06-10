package com.spring.mvc;

// 项目的构建                         ==> 使用Maven来install, update project
// 1. Create new Dynamic web project
// 2. Convert to Maven project
// 3. Add all dependencies to POM file

// 确定部署项目的context_root_path/, 在发布到tomcat时候需要使用的配置 !!
// project > properties > Web Project Settings > Context root 设置路径(artifact id名称)
public class BaseSpringMVC {

	// DispatcherServlet: Servlet Class provided by Spring framework 请求的分发器
	// 1. 来自spring-webmvc-xxx.jar > org.springframework.web.servlet
	// 2. 添加DispatcherServlet到项目中: New > Servlet > use an existing Servlet class

	// web.xml: web app deployment descriptor 发布配置描述文件
	// <welcome-file-list>: Server自动触发的web page

	// 创建 Bean configuration file: <servlet-name>-servlet.xml bean配置文件名称严格固定 !!
	// 1. 添加context, mvc namespaces名称空间

	// 配置路径的解析View Resolver
	// 1. 使用框架自带的InternalResourceViewResolver，在Beans中注入
}
