import java.util.Scanner;

public class Login
{
    public User tryLogin(Scanner sc, String role)
    {
        System.out.println("--- 로그인 ---");
        System.out.print("\n아이디를 입력하세요.");
        String id = sc.nextLine();

        System.out.print("\n비밀번호를 입력하세요.");
        String password = sc.nextLine();

        for (Register.User user : Register.userList) // Register 클래스에서 정의한 userList 사용
        {
            if (user.getrole().equals(role) && user.getID().equals(id) && user.getPassword().equals(password))
            {
                System.out.println(user.getName() + "님 로그인에 성공하셨습니다.");
                return user;
            }
        }
        System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다. 다시 시도하세요");
        return null;
    }
}
