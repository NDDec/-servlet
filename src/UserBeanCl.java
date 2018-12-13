import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by mi on 18-12-9.
 */
//这是一个处理类(处理数据库表)
//业务逻辑在这里
public class UserBeanCl
{
    //业务逻辑
    private Connection ct = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private int pageCount = 0;//一共多少页

    //返回pagecount
    public int getPageCount()
    {
        return this.pageCount;
    }

    //分页处理
    public ArrayList getResultByPage(int pageNow, int pageSize)
    {
        ArrayList al = new ArrayList();
        try {
            int rowCount = 0;//一共多少条记录

            ConnDB cd = new ConnDB();
            ct = cd.getConn();
            ps = ct.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            if (rowCount % pageSize == 0) {
                pageCount = rowCount / pageSize;
            } else {
                pageCount = rowCount / pageSize + 1;
            }

            ps = ct.prepareStatement("select * from users limit ?,?");
            ps.setInt(1, (pageNow - 1) * pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setUserId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPasswd(rs.getString(3));
                ub.setEmail(rs.getString(4));
                ub.setGrade(rs.getInt(5));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.close();
        }
        return al;
    }

    //验证用户
    public boolean checkUser(String u,String p)
    {
        boolean res = false;
        try
        {
            //得到连接
            ConnDB cd = new ConnDB();
            ct = cd.getConn();
            String sql = "select passwd from users where username = ? limit 1 ";
            ps = ct.prepareStatement(sql);
            ps.setString(1,u);
            rs = ps.executeQuery();
            if(rs.next()) {
                String dbpasswd = rs.getString(1);
                if(dbpasswd.equals(p))
                {
                    res = true;
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.close();
        }
        return res;
    }

    //删除用户
    public boolean delUser(String id)
    {
        boolean res = false;
        try
        {
            ConnDB cd = new ConnDB();
            ct = cd.getConn();
            String sql = "delete from users where userId = ? ";
            ps = ct.prepareStatement(sql);
            ps.setString(1,id);
            int num = ps.executeUpdate();//受影响的记录条数
            if(num == 1)
            {
                //用户删除成功
                res = true;
            }
            else
            {
                //删除失败
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.close();
        }
        return res;
    }

    //修改用户
    public boolean updateUser(String id,String name,String passwd,String email,String grade)
    {
        boolean res = false;
        try
        {
            ConnDB cd = new ConnDB();
            ct = cd.getConn();

            String sql = "update users set username=?,passwd=?,email=?,grade=? where userId = ? ";
            ps = ct.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,passwd);
            ps.setString(3,email);
            ps.setInt(5, Integer.parseInt(id));
            if(grade != "")
            {
                ps.setInt(4, Integer.parseInt(grade));
            }
            else if(grade == "")
            {
                ps.setInt(4, 0);
            }
            int num = ps.executeUpdate();//受影响的记录条数
            if(num == 1)
            {
                //用户修改成功
                res = true;
            }
            else
            {
                //修改失败
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.close();
        }
        return res;
    }

    //关闭资源
    public void close()
    {
        try
        {
            if(rs != null)
            {
                rs.close();
                rs = null;
            }
            if(ps != null)
            {
                ps.close();
                ps = null;
            }
            if(ct != null)
            {
                ct.close();
                ct = null;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}



