public class Student extends User
{
    private String studentId;

    public Student(String name, String studentId, String id, String password)
    {
        super(id, name, studentId, password, UserType.Student);
        this.studentId = studentId;
    }

    @Override
    public void displayInfo() {
        System.out.println("--- 학생 정보 ---");
        System.out.println("ID: " + getId());
        System.out.println("이름: " + getName());
        System.out.println("학번: " + this.studentId);
        System.out.println("-----------------");
    }
}
