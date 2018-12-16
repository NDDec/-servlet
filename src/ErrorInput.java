import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/errorinput")
public class ErrorInput extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();
            pw.println("<html>");
            pw.println("<body background = imgs/background.gif>");
            pw.println("<br><br><br><br><br><br><br><br><br><br><br><br><hr><center>");
            pw.println("<h1>输入非法，请重新输入！</h1>");
            pw.println("<a href=wel>点击返回首页</a><br>");
            pw.println("</center><hr>");
            pw.println("</body>");
            pw.println("</html>");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)
    {
        this.doGet(req,res);
    }
}
