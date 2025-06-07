enum UserType
{
    Professor, Student
}

public class User
{
    private String name;
    private String password;
    private UserType userType;
    private String id;

    public User(String name, String id, String password, UserType userType)
    {
        this.name = name;
        this.id = id;
        this.password = password;
        this.userType = userType;
    }

    private String getUserId()
    {
        return id;
    }

    private String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public UserType getUserType()
    {
        return userType;
    }
    public void display()
    {
        System.out.println("이름: " + name + ", ID: " + id);
    }
}
