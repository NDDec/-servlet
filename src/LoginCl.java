import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.nimbus.State;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mi on 18-12-5.
 */
//用户登录网站v1.0
// 用户验证
@WebServlet("/logincl")

public class LoginCl extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        //数据库url，用户名和密码
        final String db_url = "jdbc:mysql://localhost:3306/Dingdb";
        final String user = "ding";
        final String pass = "aaaa";
        Connection ct = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            //接受用户名和密码
            String u = req.getParameter("username");
            String p = req.getParameter("passwd")  ;
            //验证
            //连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            ct = DriverManager.getConnection(db_url,user,pass);
            sm = ct.createStatement();
            String sql = "select passwd from users where username = '"+u+"' limit 1";
            rs = sm.executeQuery(sql);
            if(rs.next())
            {
                String dbpasswd = rs.getString(1);
                if(dbpasswd.equals(p)) {
                    HttpSession hs = req.getSession(true);
                    hs.setMaxInactiveInterval(20);
                    hs.setAttribute("name", u);

                    res.sendRedirect("wel?uname=" + u + "&upass=" + p);
                }
                else
                {
                    res.sendRedirect("login");//要跳转的servlet的url
                }
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
