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

            int pageSize = 10;//一页显示几条记录
            int pageNow=1;//希望显示第几页
            String spagenow = req.getParameter("pageNow");
            if(spagenow != null)
            {
                    pageNow = Integer.parseInt(spagenow);
            }
            UserBeanCl ubc = new UserBeanCl();
            ArrayList al = ubc.getResultByPage(pageNow,pageSize);
            pw.println("<table border = 1");
            pw.println("<tr><th>id</th><th>name</th><th>passwd</th><th>mail</th><th>grade</th>");
            for(int i = 0;i < al.size();i++)
            {
                UserBean ub = (UserBean)al.get(i);
                pw.println("<tr>");

                pw.println("<td>"+ub.getUserId()+"</td>");

                pw.println("<td>"+ub.getUsername()+"</td>");

                pw.println("<td>"+ub.getPasswd()+"</td>");

                pw.println("<td>"+ub.getEmail()+"</td>");

                pw.println("<td>"+ub.getGrade()+"</td>");

                pw.println("</tr>");
            }
            pw.println("</table>");
            if(pageNow != 1)
            {
                pw.println("<a href=wel?pageNow="+(pageNow-1)+">上一页</a>");
            }
            for(int i = pageNow;i <= pageNow+4;i++)
            {
                pw.println("<a href=wel?pageNow="+i+">"+i+"</a>");
            }
            if(pageNow != ubc.getPageCount())
            {
                pw.println("<a href=wel?pageNow="+(pageNow+1)+">下一页</a><br>");
            }
            FileReader f = new FileReader("/home/mi/桌面/myCounter.txt");
            BufferedReader br = new BufferedReader(f);
            String numVal = br.readLine();
            br.close();
            pw.println("该网页被访问了"+numVal+"次" );
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
