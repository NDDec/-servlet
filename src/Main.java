import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by mi on 18-12-10.
 */
//用户登录网站
//主界面
@WebServlet("/main")

public class Main extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {

            //中文乱码
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();
            pw.println("<html>");
            pw.println("<body background = imgs/background.gif>");
            pw.println("<br><br><br><br><br><br><br><br><br><br><br><br><hr><center>");
           // pw.println("<img src = imgs/wel.gif><hr><center>");

            pw.println("<h1>主界面</h1>");
            pw.println("<a href=wel>管理用户</a><br>");
            pw.println("<a href=???>添加用户</a><br>");
            pw.println("<a href=???>查找用户</a><br>");
            pw.println("<a href=???>安全退出</a><br>");
            pw.println("</body>");
            pw.println("</html>");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)
    {
        this.doGet(req,res);
    }
}
