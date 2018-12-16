import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by mi on 18-12-5.
 */
//欢迎界面
@WebServlet("/wel")

public class Wel extends HttpServlet {
    private String passwd;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        String name_cookie = null;
        String passwd_cookie = null;
        try {
            //中文乱码
            res.setContentType("text/html;charset=gbk");
            HttpSession hs = req.getSession(true);
            PrintWriter pw = res.getWriter();
            String val = (String) hs.getAttribute("name");
            pw.println("<body background = imgs/background.gif>");
            pw.println("<img src = imgs/wel.gif>&nbsp;&nbsp;&nbsp;&nbsp;欢迎您："+val+"<img src = imgs/qq.gif height = 35 width = 35>");
            pw.println("<hr><center>");
            //头像正确的处理应该从数据库中查询，这里暂时按照直接显示,添加一个字段
            pw.println("<h1>管理用户</h1>");
            if(val == null)
            {
                try
                {
                    //在session中判断没有信息之后，需要判断cookie中是否有信息
                    Cookie [] allcookies = req.getCookies();
                    if(allcookies != null)
                    {
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
                        if(name_cookie != null && passwd_cookie != null)
                        {
                            res.sendRedirect("logincl?username="+name_cookie+"&passwd="+passwd_cookie+" ");
                            return;
                        }
                    }
                    res.sendRedirect("login?info=error");
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            int pageSize = 10;//一页显示几条记录
            int pageNow=1;//希望显示第几页
            String spagenow = req.getParameter("pageNow");
            UserBeanCl ubc = new UserBeanCl();
            if(spagenow != null)
            {
                double pageTmp = Double.valueOf(spagenow);
                if(pageTmp > ubc.getPageCount(pageSize))
                {
                    //如果跳转页数大于最大页数，跳转至最后一页
                    pageNow = ubc.getPageCount(pageSize);
                }
                else
                {
                    pageNow = (int) pageTmp;
                }
            }
            ArrayList al = ubc.getResultByPage(pageNow,pageSize);
            pw.println("<table border = 1>");
            pw.println("<tr bgcolor = silver><th>id</th><th>name</th><th>passwd</th><th>mail</th><th>grade</th><th>修改用户</th><th>删除用户</th>");
            for(int i = 0;i < al.size();i++)
            {
                UserBean ub = (UserBean)al.get(i);
                pw.println("<tr>");

                pw.println("<td>"+ub.getUserId()+"</td>");

                pw.println("<td>"+ub.getUsername()+"</td>");

                pw.println("<td>"+ub.getPasswd()+"</td>");

                pw.println("<td>"+ub.getEmail()+"</td>");

                pw.println("<td>"+ub.getGrade()+"</td>");

                pw.println("<td><a href=update?uid="+ub.getUserId()+"&uname="+ub.getUsername()+"&upass="+ub.getPasswd()+"&uemail="+ub.getEmail()+"&ugrade="+ub.getGrade()+">修改用户</a></td>");

                pw.println("<td><a href=delusercl?userid="+ub.getUserId()+">删除用户</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            if(pageNow != 1)
            {
                pw.println("<a href=wel?pageNow="+(pageNow-1)+">上一页</a>");
            }
            for(int i = pageNow; i <= pageNow+4; i++)
            {
                pw.println("<a href=wel?pageNow="+i+">"+i+"</a>");
            }
            if(pageNow != ubc.getPageCount(pageSize)) {
                pw.println("<a href=wel?pageNow=" + (pageNow + 1) + ">下一页</a><br>");
            }
            //制定跳转到某页，实际是一个表单
            //考虑的问题：输入页数过大，输入不是数字
            pw.println("<form action=wel>");
            pw.println("<input type = text name = pageNow>");
            pw.println("<input type = submit value = go>");
            pw.println("</form><br>");
            String numVal = this.getServletContext().getAttribute("visitTimes").toString();
            pw.println("该网页被访问了"+numVal+"次<br>" );
            pw.println("您的ip="+req.getRemoteAddr()+"");
            pw.println(" 您的机器名="+req.getRemoteHost()+"<br>");
            pw.println("<br><a href = login>重新登录</a>");
            pw.println("</body>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)
    {
        this.doGet(req,res);
    }
}
