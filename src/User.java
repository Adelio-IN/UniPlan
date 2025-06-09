public abstract class User
{
    private String name;
    private String id;
    private String password;
    private String studentId;

    protected UserType role;

    public User(String id, String name, String studentId, String password, UserType role)
    {
        this.name = name;
        this.id = id;
        this.password = password;
        this.studentId = studentId;
        this.role = role;
    }

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

    public abstract void displayInfo();
}
