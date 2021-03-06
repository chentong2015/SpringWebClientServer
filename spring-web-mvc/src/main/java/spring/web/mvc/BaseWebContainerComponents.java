package spring.web.mvc;

import base.IParseDoc;

import java.util.ServiceLoader;

// TODO: 如何往容器中注入Web三大组件(servlet, filter, listener)
// 1. 配置xml文件
//    web.xml;  dispatcher-servlet.xml
// 2. 使用特定的注解
//    @WebServlet("/HomeServlet")      => MyServlet extends HttpServlet
//    @WebListener                     => MyListener implements ServletContextListener
//    @WebFilter(value="/hello")       => MyFilter implements Filter
// 3. SPI机制: 基于Servlet3.0规范
//    3.1 在/resources classpath路径下面配置指定的文件名称，使用接口的全路径
//    3.2 在文件中配置使用的具体配置类型的全路径
//    3.3 要求配置的类型必须有无参的构造器
public class BaseWebContainerComponents {

    // TODO: 通过ServiceLoader类读取前面配置的文件，将配置的类型通过反射创建对象
    public static void main(String[] args) {
        // 彻底解耦的实现，只需要改配置文件
        // 使用文件中对接口IParseDoc实现的类型
        ServiceLoader<IParseDoc> iParseDocs = ServiceLoader.load(IParseDoc.class);
        for (IParseDoc iParseDoc : iParseDocs) {
            iParseDoc.parse();
        }
    }
}
