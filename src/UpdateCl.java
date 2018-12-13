import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//修改用户处理
@WebServlet("/updatecl")

public class UpdateCl extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            String id = req.getParameter("userid");
            UserBeanCl ubc = new UserBeanCl();
            if(ubc.updateUser(req.getParameter("newid"),
                    req.getParameter("newname"),
                    req.getParameter("newpasswd"),
                    req.getParameter("newemail"),
                    req.getParameter("newgrade")))
            {
                res.sendRedirect("ok");
            }
            else
            {
                res.sendRedirect("no");
            }
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
