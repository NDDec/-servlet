/**
 * Created by mi on 18-12-5.
 */


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/myWebSite/Helloworld")

public class HelloWorld implements Servlet
{

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init it first");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("service it");
        PrintWriter pw = servletResponse.getWriter();
        pw.println("ding is so cool");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy!");
    }
}