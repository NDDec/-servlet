import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by mi on 18-12-9.
 */
//从数据库中得到连接
public class ConnDB
{
    private Connection ct = null;
    public Connection getConn()
    {
        final String db_url = "jdbc:mysql://localhost:3306/Dingdb";
        final String user = "ding";
        final String pass = "aaaa";
        try
        {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接
            ct = DriverManager.getConnection(db_url,user,pass);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ct;
    }
}
