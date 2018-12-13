import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//删除用户处理
@WebServlet("/delusercl")
public class DelUserCl extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            String id = req.getParameter("userid");
            UserBeanCl ubc = new UserBeanCl();
            if(ubc.delUser(id))
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
