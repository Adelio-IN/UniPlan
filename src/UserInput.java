import java.util.Scanner;

public class UserInput
{
    private static final Scanner scanner = new Scanner(System.in);
    private static String password;

    public static int getUserType()
    {
        System.out.print("사용자 유형을 선택하세요 (1: 학생, 2: 교수): ");
        return scanner.nextInt();
    }

    public static String getUserId()
    {
        System.out.print("ID를 입력하세요: ");
        return scanner.next();
    }

    public static String getUserName()
    {
        System.out.print("이름을 입력하세요: ");
        return scanner.next();
    }

    public static String getStudentId()
    {
        System.out.print("학번을 입력하세요: ");
        return scanner.next();
    }

    public static String getLabLocation()
    {
        System.out.print("연구실 위치를 입력하세요: ");
        return scanner.next();
    }

    public static String getPassword()
    {
        return password;
    }

    public static void setPassword(String password) {
        UserInput.password = password;
    }
}