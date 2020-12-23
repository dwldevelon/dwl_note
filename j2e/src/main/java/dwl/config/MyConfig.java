package dwl.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wenlong.ding
 * @date 2020/12/18 10:01
 */
@Configuration
public class MyConfig {
//    @Bean
//    public ServletRegistrationBean myServlet(){
//
//        ServletRegistrationBean<MyServlet> servletServletRegistrationBean=new ServletRegistrationBean<>();
//        servletServletRegistrationBean.setServlet(new MyServlet());
//        servletServletRegistrationBean.addUrlMappings("/hello");
//
//
//        Servlet servlet = new Servlet() {
//            @Override
//            public void init(ServletConfig config) throws ServletException {
//
//            }
//
//            @Override
//            public ServletConfig getServletConfig() {
//                return null;
//            }
//
//            @Override
//            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//
//            }
//
//            @Override
//            public String getServletInfo() {
//                return null;
//            }
//
//            @Override
//            public void destroy() {
//
//            }
//        };
//    }
}
