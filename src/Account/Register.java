import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User
{
    private String name;
    private String id;
    private String password;

    public User(String name, String id, String password)
    {
        this.name = name;
        this.id = id;
        this.password = password;
    }
    public void display()
    {
        System.out.println("이름: " + name + ", ID: " + id);
    }
}
public class Register
{
    public static boolean isValid(String input)
    {
        return input.matches("^[a-zA-Z0-9]*$");
    }
    public static String getValidInput(Scanner sc, String fieldName)
    {
        String input;
        while (true)
        {
            System.out.printf("%s 영문(대소문자)/숫자만 입력하세요.\n 'exit'를 입력하면 종료됩니다", fieldName);
            input = sc.nextLine().strip();
            if (input.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (isValid(input))
            {
                break;
            }
            else
            {
                System.out.println("Warning! 영문 대소문자와 숫자만 입력할 수 있습니다. 다시 입력하세요.");
            }
        }
        return input;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        List<User> UserList = new ArrayList<>();

        System.out.println("회원가입을 시작합니다. ID와 비밀번호를 입력하세요. 중단하려면, exit를 입력하세요.");
        System.out.println("ID와 비밀번호는 영문 대소문자, 숫자만 입력 가능합니다.");
        System.out.println("\n등록할 아이디를 입력하세요.: ");
        String id = sc.nextLine();
        if (id.equals("exit"))
        {
            System.out.println("회원가입이 종료되었습니다.");
            return;
        }
        System.out.println("\n등록할 비밀번호를 입력하세요.: ");
        String password = sc.nextLine();
        if (password.equals("exit"))
        {
            System.out.println("회원가입이 종료되었습니다.");
            return;
        }
        System.out.println("\n등록할 이름을 입력하세요.: ");
    }
}
