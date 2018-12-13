import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mi on 18-12-5.
 */
//用户登录网站
// 用户验证
@WebServlet("/logincl")

public class LoginCl extends HttpServlet {
    //重写init函数
    public void init()
    {
        try
        {
            //添加网页访问次数的功能
            FileReader f = new FileReader("/home/mi/桌面/myCounter.txt");
            BufferedReader br = new BufferedReader(f);
            String numVal = br.readLine();
            br.close();
            if(numVal == null)
            {
                numVal = "0";
            }
            //将numVal值放入到servletcontext中
            this.getServletContext().setAttribute("visitTimes",numVal);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    //重写distroy函数
    public void destroy()
    {
        try
        {
            FileWriter f1 = new FileWriter("/home/mi/桌面/myCounter.txt");
            BufferedWriter bw = new BufferedWriter(f1);
            String times = this.getServletContext().getAttribute("visitTimes").toString();
            bw.write(times);
            bw.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Connection ct = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            //接受用户名和密码
            String u = req.getParameter("username");
            String p = req.getParameter("passwd");
            //验证
            //连接数据库,调用UserBeanCl
            UserBeanCl ubc = new UserBeanCl();
            if(ubc.checkUser(u,p))
            {
                //添加进cookie
                String keep = req.getParameter("keep");
                if(keep != null)
                {
                    Cookie name_cookie = new Cookie("myname", u);
                    Cookie pass_cookie = new Cookie("mypasswd", p);
                    name_cookie.setMaxAge(14 * 24 * 3600);
                    pass_cookie.setMaxAge(14 * 24 * 3600);
                    res.addCookie(name_cookie);
                    res.addCookie(pass_cookie);
                }
                HttpSession hs = req.getSession(true);
                hs.setMaxInactiveInterval(360);
                hs.setAttribute("name", u);
                String times = this.getServletContext().getAttribute("visitTimes").toString();
                this.getServletContext().setAttribute("visitTimes",(Integer.parseInt(times)+1)+"");
                res.sendRedirect("main");
            }
            else
            {
                res.sendRedirect("login");//要跳转的servlet的url
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null) {
                    rs.close();
                }
                if(sm != null) {
                    sm.close();
                }
                if(ct != null) {
                    ct.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)
    {
        this.doGet(req,res);
    }
}
