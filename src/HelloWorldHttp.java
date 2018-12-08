import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by mi on 18-12-5.
 */
@WebServlet("/myWebSite/HelloWorldHttp")
public class HelloWorldHttp extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            PrintWriter pw = res.getWriter();
            pw.println("ding is so cool three");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)
    {
        this.doGet(req,res);
    }
}
