import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//修改用户界面
@WebServlet("/update")

public class Update extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {

            //中文乱码
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();
            pw.println("<html>");
            pw.println("<body background = imgs/background.gif>");
            pw.println("<img src = imgs/wel.gif><hr><center>");

            pw.println("<h1>修改用户界面</h1>");
            pw.println("<form action = updatecl>");
            pw.println("<table border = 1>");
            pw.println("<tr><td>id</td><td><input readonly name = newid type = text value="+req.getParameter("uid")+"></td></tr>");
            pw.println("<tr><td>name</td><td><input name = newname type = text value="+req.getParameter("uname")+"></td></tr>");
            pw.println("<tr><td>passwd</td><td><input name = newpasswd type = text value="+req.getParameter("upass")+"></td></tr>");
            pw.println("<tr><td>email</td><td><input name = newemail type = text value="+req.getParameter("uemail")+"></td></tr>");
            pw.println("<tr><td>grade</td><td><input name = newgrade type = text value="+req.getParameter("ugrade")+"></td></tr>");
            pw.println("<tr><td colspan=2><input type = submit value = 修改用户></td></tr>");
            pw.println("</table></form>");
            pw.println("</center><hr>");
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
