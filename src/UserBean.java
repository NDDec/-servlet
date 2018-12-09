/**
 * Created by mi on 18-12-9.
 */
//这是一个UserBean，与users表映射
public class UserBean
{
    private int userId;
    private String username;
    private String passwd;
    private String email;
    private int grade;

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public int getUserId()
    {
        return this.userId;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }
    public String getPasswd()
    {
        return this.passwd;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setGrade(int grade)
    {
        this.grade = grade;
    }
    public int getGrade()
    {
        return this.grade;
    }
}
