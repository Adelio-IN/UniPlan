import java.util.Scanner;

public class Login
{
    public User tryLogin(Scanner sc, String roleStr)
    {
        System.out.println("\n--- 로그인 유형 선택 ---");
        System.out.println("1. 교수");
        System.out.println("2. 학생");
        System.out.print("로그인할 유형을 선택하세요 (1 또는 2): ");
        String choice = sc.nextLine().trim();

        UserType targetType;

        switch (choice) {
            case "1":
                targetType = UserType.Professor;
                System.out.println("\n--- 교수 로그인 ---");
                break;

            case "2":
                targetType = UserType.Student;
                System.out.println("\n--- 학생 로그인 ---");
                break;

            default:
                System.out.println("1 또는 2를 입력해야 합니다.");
                return null;
        }

        System.out.print("아이디: ");
        String id = sc.nextLine().trim();
        System.out.print("비밀번호: ");
        String password = sc.nextLine().trim();

        for (Register.User user : Register.userList)
        {
            if (user.getRole() == targetType && user.getID().equals(id) && user.getPassword().equals(password))
            {
                System.out.println(user.getName() + "님 로그인에 성공하셨습니다.");
            }
        }
        System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다. 다시 시도하세요");
        return null;
    }
}
