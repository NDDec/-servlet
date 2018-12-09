import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by mi on 18-12-5.
 */
//欢迎界面
@WebServlet("/wel")

public class Wel extends HttpServlet {
    private String passwd;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        //String u = req.getParameter("uname");
        //String p = req.getParameter("upass");
        final String url = "jdbc:mysql://localhost:3306/Dingdb";
        final String name = "ding";
        final String passwd = "aaaa";
        String name_cookie = null;
        String passwd_cookie = null;

        Connection ct = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            HttpSession hs = req.getSession(true);
            PrintWriter pw = res.getWriter();
            String val = (String) hs.getAttribute("name");
            pw.println("<body><center>");
            pw.println("<img src = imgs/1.gif><br>");
            pw.println("welcome to ding's website! " + val + "<br>");

            if(val == null) {
                try {
                    //在session中判断没有信息之后，需要判断cookie中是否有信息
                    Cookie [] allcookies = req.getCookies();
                    if(allcookies != null) {
                        for (int i = 0; i < allcookies.length; i++)
                        {
                            Cookie temp = allcookies[i];
                            if(temp.getName().equals("myname"))
                            {
                                name_cookie = temp.getValue();
                            }
                            if(temp.getName().equals("mypasswd"))
                            {
                                passwd_cookie = temp.getValue();
                            }
                        }
                        if(!name_cookie.equals("") && !passwd_cookie.equals(""))
                        {
                            res.sendRedirect("logincl?username="+name_cookie+"&passwd="+passwd_cookie+" ");
                            return;
                        }
                    }
                    else {
                        res.sendRedirect("login?info=error");
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            int pagesize = 3;//一页显示几条记录
            int pagenow=1;//希望显示第几页
            int rowcount = 0;//一共多少条记录
            int pagecount = 0;//共有几页

            String spagenow = req.getParameter("pageNow");
            if(spagenow != null)
            {
                    pagenow = Integer.parseInt(spagenow);
            }

            Class.forName("com.mysql.jdbc.Driver");
            ct = DriverManager.getConnection(url,name,passwd);
            ps = ct.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            if(rs.next())
            {
                rowcount = rs.getInt(1);
            }
            if(rowcount%pagesize==0)
            {
                pagecount = rowcount/pagesize;
            }
            else
            {
                pagecount = rowcount/pagesize + 1;
            }

            ps=ct.prepareStatement("select * from users limit ?,?");
            ps.setInt(1,(pagenow-1)*pagesize);
            ps.setInt(2,pagesize);
            rs = ps.executeQuery();
            pw.println("<table border = 1");
            pw.println("<tr><th>id</th><th>name</th><th>passwd</th><th>mail</th><th>grade</th>");
            while(rs.next())
            {
                pw.println("<tr>");
                pw.println("<td>"+rs.getInt(1)+"</td>");

                pw.println("<td>"+rs.getString(2)+"</td>");

                pw.println("<td>"+rs.getString(3)+"</td>");

                pw.println("<td>"+rs.getString(4)+"</td>");

                pw.println("<td>"+rs.getInt(5)+"</td>");

                pw.println("</tr>");
            }
            pw.println("</table>");
            if(pagenow != 1)
            {
                pw.println("<a href=wel?pageNow="+(pagenow-1)+">上一页</a>");
            }
            for(int i = pagenow;i <= pagenow+4;i++)
            {
                pw.println("<a href=wel?pageNow="+i+">"+i+"</a>");
            }
            if(pagenow != pagecount)
            {
                pw.println("<a href=wel?pageNow="+(pagenow+1)+">下一页</a>");
            }
            pw.println("<br><a href = login>重新登录</a>");
            pw.println("</center></body>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)
    {
        this.doGet(req,res);
    }
}
