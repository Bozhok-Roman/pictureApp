package picture.server;

import picture.dao.PictDaoImpl;
import picture.connection.ConnectionManagerFactory;
import picture.servlets.PicsServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import userLogin.servlets.LoginServlet;


import javax.servlet.ServletException;
import java.io.File;

public class Server {

    public static void main(String[] args) throws LifecycleException, ServletException {
        String contextPath = "";
        String appBase = new File("src/main/java/web").getAbsolutePath();
        String docBase = new File("src/main/webapp").getAbsolutePath();
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(".");
        tomcat.setPort(8080);
        PictDaoImpl pictDao = new PictDaoImpl(ConnectionManagerFactory.getH2ConnectionManager());
        pictDao.create();


        Context context = tomcat.addWebapp(contextPath, docBase);


        String servletName = PicsServlet.class.getSimpleName();
        String servletClassName = PicsServlet.class.getName();

        String urlPattern = "/pics";

        tomcat.addServlet(contextPath, servletName, servletClassName);
        context.addServletMapping(urlPattern, servletName);

        servletName = LoginServlet.class.getSimpleName();
        servletClassName = LoginServlet.class.getName();

        urlPattern = "/login";

        tomcat.addServlet(contextPath, servletName, servletClassName);
        context.addServletMapping(urlPattern, servletName);




        tomcat.start();
        tomcat.getServer().await();
    }
}
