import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mi on 18-12-5.
 */
@WebServlet("/MyWebSite/HelloWorldGen")
public class HelloWorldGen extends GenericServlet
{

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try
        {
            PrintWriter pw = servletResponse.getWriter();
            pw.println("ding is so cool again");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
