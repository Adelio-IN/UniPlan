import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Register
{
    public static List<User> userList = new ArrayList<>();

    static class User
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

        public void display()
        {
            System.out.println("이름: " + name + ", ID: " + id);
        }

        public Object getID()
        {
            return id;
        }
    }

    public static boolean isValidAlphaNumeric(String input)
    {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean isValidIdFormat(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public static boolean isIdTaken(List<User> userList, String id) {
        {
            for (User user : userList) {
                if (user.getID().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getValidAlphaNumericInput(Scanner sc, String fieldName)
    {
        String input;
        while (true)
        {
            System.out.printf("%s 를 입력하세요. " +
                    "\n ! 영문 대/소문자, 숫자만 입력가능합니다. 중단하려면, 'exit'를 입력하세요", fieldName);
            input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }
            if (input.isEmpty()) {
                System.out.println(fieldName + "은 비워둘 수 없습니다. 재시도하세요.");
                continue;
            }
            if (isValidAlphaNumeric(input)) {
                break;
            } else {
                System.out.println(fieldName + "영문 대/소문자와 숫자만 입력할 수 있습니다. 재시도하세요.");
            }
        }
        return input;
    }
    public static String getValidIdInput(Scanner sc, List<User> userList)
    {
        String id;
        while (true)
        {
            System.out.print("등록할 아이디를 입력하세요 (영문+숫자 조합, 8자 이상). 'exit' 입력 시 종료: ");
            id = sc.nextLine();
            if (id.equalsIgnoreCase("exit")) {
                return "exit";
            }
            if (id.isEmpty()) {
                System.out.println("아이디는 비워둘 수 없습니다. 다시 입력하세요.");
                continue;
            }
            if (!isValidIdFormat(id)) {
                System.out.println("영문자와 숫자를 포함하여 8자 이상이어야 합니다. 다시 입력하세요.");
                continue;
            }
            if (isIdTaken(userList, id)) {
                System.out.println("⚠️ [" + id + "]은(는) 이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요.");
                continue;
            }
            break;
        }
        return id;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<User> userList = new ArrayList<>();

        System.out.println("✨ 회원가입을 시작합니다. (중단하려면 언제든지 'exit'를 입력하세요)\n");

        while (true) {
            String id = getValidIdInput(sc, userList);
            if (id.equalsIgnoreCase("exit")) {
                System.out.println("회원가입을 종료합니다.");
                break;
            }

            String password = getValidAlphaNumericInput(sc, "비밀번호");
            if (password.equalsIgnoreCase("exit")) {
                System.out.println("회원가입을 종료합니다.");
                break;
            }

            String name = getValidAlphaNumericInput(sc, "사용자 이름");
            if (name.equalsIgnoreCase("exit")) {
                System.out.println("회원가입을 종료합니다.");
                break;
            }
            System.out.println("✅ [" + name + " (" + id + ")]님의 회원가입이 완료되었습니다!");
            System.out.println("------------------------------------");
        }
        System.out.println("회원가입 프로그램을 종료합니다.");
        sc.close();
    }
}