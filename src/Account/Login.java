import java.util.Scanner;

public class Login
{
    public User tryLogin(Scanner sc)
    {
        System.out.println("\n--- 로그인 유형 선택 (1 또는 2)---");
        System.out.println("1. 교수");
        System.out.println("2. 학생");
        System.out.println("3. exit");
        System.out.println("4. 이전 메뉴");

        String choice = sc.nextLine().trim();
        UserType targetType;
        String roleString;

        switch (choice)
        {
            case "1":
                targetType = UserType.Professor;
                roleString = "교수";
                System.out.println("\n--- 교수 로그인 ---");
                break;

            case "2":
                targetType = UserType.Student;
                roleString = "학생";
                System.out.println("\n--- 학생 로그인 ---");
                break;
            case "3":
                System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                Alarm.stopScheduler();
                sc.close();
                System.exit(0);
            case "4":
                return null;
            default:
                System.out.println("잘못된 선택입니다");
                return null;
        }

        System.out.print("아이디: ");
        String id = sc.nextLine().trim();
        System.out.print("비밀번호: ");
        String password = sc.nextLine().trim();

        for (User user : Register.userList) {
            if (user.getRole() == targetType && user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
            System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다. 다시 시도하세요");
        }
        return null;
    }
}
