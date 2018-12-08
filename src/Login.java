import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by mi on 18-12-5.
 */
//用户登录网站v1.0
//登录界面
    @WebServlet("/login")

public class Login extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {

            //中文乱码
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();
            pw.println("<html>");
            pw.println("<body>");
            String info = req.getParameter("info");
            if(info != null)
            {
                pw.println("<h3>请先输入用户名和密码</h3>");
            }
            pw.println("<h1>登录界面</h1>");
            pw.println("<form action=logincl method=get>");
            pw.println("用户名:<input type=text name = username><br>");
            pw.println("密码:<input type = text name = passwd><br>");
            pw.println("<input type = submit value=login><br>");
            pw.println("</form>");
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
