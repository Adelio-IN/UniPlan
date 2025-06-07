enum UserType
{
    Professor, Student
}
public class User
{
    private String name;
    private String password;
    private String time;
    private UserType userType;
    private String userId;

    public User(String name, String userId, String password, UserType userType)
    {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.userType = userType;
    }

    private String getUserId()
    {
        return userId;
    }

    private String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public UserType userType()
    {
        return userType();
    }
}
