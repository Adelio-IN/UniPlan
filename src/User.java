enum UserType
{
    Student, Professor
}
public class User
{
    private String name;
    private String password;
    private UserType role;
    private String id;
    private String classNumber;

    public User(String name, UserType role, String classNumber, String id, String password)
    {
        this.name = name;
        this.role = role;
        this.classNumber = classNumber;
        this.id = id;
        this.password = password;

    }

    // --- Getter 메소드들 ---
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public String getPassword()
    {
        return password;
    }
    public UserType getRole()
    {
        return role;
    }
    public String getClassNumber()
    {
        return classNumber;
    }
}
