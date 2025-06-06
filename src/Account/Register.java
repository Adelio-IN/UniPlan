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
        private String role;

        public User(String name, String role, String id, String password)
        {
            this.name = name;
            this.id = id;
            this.password = password;
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

        public void display()
        {
            System.out.println("이름: " + name + ", ID: " + id);
        }

        public Object getID()
        {
            return id;
        }
        public String getrole()
        {
        return role;
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
    public static boolean isValidName(String input)
    {
        if (input == null || input.isEmpty())
        {
            return false;
        }
        return input.matches("^[a-zA-Z가-힣]*$");
    }
    public static String getValidNameInput(Scanner sc)
    {
        String input;
        while(true)
        {
            System.out.print("사용자 이름을 입력하세요. 한글/영문으로 입력가능하며 중단하려면 'exit'를 입력하세요.");
            input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("이름은 비워둘 수 없습니다.");
                continue;
            }
            if (isValidName(input))
            {
                break;
            }
            else
            {
                System.out.println("한글과 영문 대/소문자만 입력할 수 있습니다.");
            }
        }
        return input;
    }

    public static String getValidAlphaNumericInput(Scanner sc, String fieldName)
    {
        String input;
        while (true)
        {
            System.out.printf("%s 를 입력하세요. 영문(대/소문자), 숫자만 입력가능합니다. 중단하려면, 'exit'를 입력하세요 \n1", fieldName);
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

    public static String getValidRole(Scanner sc)
    {
        String input;
        while(true)
        {
            System.out.print("[교수 / 학생] 중 역할을 선택하세요. 중단하려면 언제든지 'exit'를 입력하세요");
            input = sc.nextLine();
            if(input.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (input.equals("교수") || input.equals("<UNK>"))
            {
                break;
            }
            else
            {
                System.out.println("'교수'와 '학생'만 입력할 수 있습니다.");
            }
        }
        return input;
    }

    public static void processRegister(Scanner sc)
    {
        System.out.println("✨ 회원가입을 시작합니다. (중단하려면 언제든지 'exit'를 입력하세요)\n");

        String role = getValidRole(sc);
        if (role.equalsIgnoreCase("exit"))
        {
            System.out.println("회원가입을 종료합니다");
            return;
        }
        String id = getValidIdInput(sc, userList);
        if (id.equalsIgnoreCase("exit"))
        {
            System.out.println("회원가입을 종료합니다");
            return;
        }

        String password = getValidAlphaNumericInput(sc, "비밀번호");
        if (password.equalsIgnoreCase("exit"))
        {
            System.out.println("회원가입을 종료합니다");
        }

        String name = getValidNameInput(sc);
        if (name.equalsIgnoreCase("exit"))
        {
            System.out.println("회원가입을 중단합니다.");
            return;
        }
        System.out.println("✅ [" + name + " (" + id + ")]님의 회원가입이 완료되었습니다!");
        System.out.println("------------------------------------");
    }
}