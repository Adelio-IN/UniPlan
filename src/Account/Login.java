import java.util.Scanner;

public class Login
{
    private Scanner sc;

    public Login()
    {
        this.sc = new Scanner(System.in);
    }
    public Login tryLogin()
    {
        System.out.println("---로그인을 시작합니다---");
        System.out.print("아이디를 입력하세요.");
        String id = this.sc.nextLine();

        System.out.print("비밀번호를 입력하세요: ");
        String password = sc.nextLine().trim();
    }
}
